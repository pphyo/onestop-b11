import {useContext, createContext} from 'react';

export type User = {
    name: string
    isLogin: boolean;
}

type UserType = {
    user: User;
    login: (user: User) => void;
    logout: (user: User) => void;
}

export const UserContext = createContext<UserType | undefined>(undefined);

export function useUser() {
    const context = useContext(UserContext);
    if(!context) {
        throw new Error("useUser must be use within UserProvider");
    }
    return context;
}