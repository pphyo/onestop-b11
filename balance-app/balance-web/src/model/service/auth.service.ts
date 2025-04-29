import axiosInstance from "@/axios/axios-instance";
import { SignInDto, SignUpDto, UserDto } from "../dto/auth.dto";
import { BASE_API, ENDPOINT } from "@/lib/consts.type";

const AUTH_API: ENDPOINT = `${BASE_API}/auth`;

class AuthService {

    private static instance: AuthService;
    private USER_KEY: string = "com.jdc.balance.user";

    private constructor() {}

    public static getInstance(): AuthService {
        if(!AuthService.instance) {
            AuthService.instance = new AuthService;
        }
        return AuthService.instance;
    }

    async signIn(dto: SignInDto): Promise<UserDto> {
        const resp: UserDto = await axiosInstance.post(`${AUTH_API}/signin`, dto).then(res => res.data);
        localStorage.setItem(this.USER_KEY, JSON.stringify(resp));
        return resp;
    }

    async signUp(dto: SignUpDto): Promise<boolean> {
        const resp: boolean = await axiosInstance.post(`${AUTH_API}/signup`, dto);
        return resp;
    }

    async refresh(): Promise<UserDto> {
        const refreshToken = this.getCurrentUser()?.refreshToken;
        const refreshedUser = await axiosInstance.post<UserDto>(`${AUTH_API}/refresh`, {token: refreshToken}).then(res => res.data);
        if(refreshedUser) {
            localStorage.setItem(this.USER_KEY, JSON.stringify(refreshedUser));
        }
        return refreshedUser;
    }

    getCurrentUser(): UserDto | null {
        const result = localStorage.getItem(this.USER_KEY);
        return result ? JSON.parse(result) : null;
    }

    signOut() {
        localStorage.removeItem(this.USER_KEY);
    }

}

export const getAuthService = () => AuthService.getInstance();