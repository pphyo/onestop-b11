interface UserDto {
    name: string;
    username: string;
    admin: boolean;
    accessToken: string;
    refreshToken: string;
}

interface SignInDto {
    username: string;
    password: string;
}

interface SignUpDto {
    name: string;
    username: string;
    password: string;
}

export type { UserDto, SignInDto, SignUpDto };