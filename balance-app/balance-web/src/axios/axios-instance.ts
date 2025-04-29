import { BalanceErrorPayload, useErrorStore } from "@/error/error.store";
import { BASE_API, HEADER_JSON } from "@/lib/consts.type";
import { getAuthService } from "@/model/service/auth.service";
import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from "axios";

const axiosInstance = axios.create({
    baseURL: BASE_API,
    timeout: 10000,
    headers: HEADER_JSON
});

type FailRequestQueue = {
    resolve: (token: string) => void;
    reject: (error: AxiosError<BalanceErrorPayload>) => void;
}

let isRefreshing = false;
let failRequestsQueue: Array<FailRequestQueue> = [];

const processQueue = (
                        error: AxiosError<BalanceErrorPayload> | null,
                        token: string | null = null
                    ) => {
    failRequestsQueue.forEach((queue) => {
        if(error) {
            queue.reject(error);
        } else {
            queue.resolve(String(token));
        }
    });
    failRequestsQueue = [];
};

axiosInstance.interceptors.response.use(
    (response: AxiosResponse) => response,
    async (error: AxiosError<BalanceErrorPayload>) => {
        const originalRequest = error.config as AxiosRequestConfig & { _retry?: boolean };

        const authService = getAuthService();

        if(error.response?.status === 401 && !originalRequest._retry) {
            if(isRefreshing) {
                return new Promise<string>((resolve, reject) => {
                    failRequestsQueue.push({
                        resolve,
                        reject: (error: AxiosError<BalanceErrorPayload>) => reject(error)
                    });
                }).then((token: string) => {
                    originalRequest.headers = originalRequest.headers || {};
                    originalRequest.headers["Authorization"] = `Bearer ${token}`;
                    return axiosInstance(originalRequest);
                }).catch((err: AxiosError<BalanceErrorPayload>) => Promise.reject(err));
            }

            originalRequest._retry = true;
            isRefreshing = true;

            try {
                const refreshedUser = await authService.refresh();
                const newAccessToken = refreshedUser.accessToken;

                originalRequest.headers = originalRequest.headers || {};
                originalRequest.headers["Authorization"] = `Bearer ${newAccessToken}`;

                processQueue(null, newAccessToken);

                return axiosInstance(originalRequest);
            } catch(refreshedError: unknown) {
                const typedError = refreshedError as AxiosError<BalanceErrorPayload>;
                processQueue(typedError);

                const apiError: BalanceErrorPayload = typedError.response?.data || {
                    errorAt: new Date(),
                    title: "Session Invalid",
                    messages: ["Your session is invalid. Please sign in to continue."]
                };
                useErrorStore.getState().setError({
                    errorAt: apiError.errorAt,
                    title: apiError.title,
                    messages: apiError.messages
                });
                return Promise.reject(typedError);
            } finally {
                isRefreshing = false;
            }
        }

        const apiError = error.response?.data || {
            errorAt: new Date(),
            title: "API Error",
            messages: ["Something went wrong."]
        };

        useErrorStore.getState().setError({
            errorAt: apiError.errorAt,
            title: apiError.title,
            messages: apiError.messages
        });
        return Promise.reject(error);

    }
);

axiosInstance.interceptors.request.use((config) => {
    const authService = getAuthService();
    const token: string | undefined = authService.getCurrentUser()?.accessToken;

    if(token)
        config.headers["Authorization"] = `Bearer ${token}`;

    return config;
});

export default axiosInstance;