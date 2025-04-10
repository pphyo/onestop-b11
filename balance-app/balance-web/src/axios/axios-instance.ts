import { BASE_API, HEADER_JSON } from "@/lib/consts.type";
import { getAuthService } from "@/model/service/auth.service";
import axios from "axios";

const axiosInstance = axios.create({
    baseURL: BASE_API,
    timeout: 10000,
    headers: HEADER_JSON
})

axiosInstance.interceptors.request.use((config) => {
    const authService = getAuthService();
    const token: string | undefined = authService.getCurrentUser()?.accessToken;

    if(token)
        config.headers["Authorization"] = `Bearer ${token}`;

    return config;
})

export default axiosInstance;