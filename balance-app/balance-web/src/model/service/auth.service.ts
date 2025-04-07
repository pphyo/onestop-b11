import axiosInstance from "@/axios/axios-instance";
import { SignInDto, SignUpDto, UserDto } from "../dto/auth.dto";
import { BASE_API, ENDPOINT, HEADER_JSON } from "@/lib/consts.type";

const AUTH_API: ENDPOINT = `${BASE_API}/auth/`;

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
        const resp: UserDto = await axiosInstance.post(`${AUTH_API}/signin`, dto, {headers: HEADER_JSON});
        localStorage.setItem(this.USER_KEY, JSON.stringify(resp));
        return resp;
    }

    async signUp(dto: SignUpDto): Promise<boolean> {
        const resp: boolean = await axiosInstance.post(`${AUTH_API}/signup`, dto, {headers: HEADER_JSON});
        return resp;
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