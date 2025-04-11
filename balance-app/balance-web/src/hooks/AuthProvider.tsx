import { SignInDto, UserDto } from '@/model/dto/auth.dto';
import { getAuthService } from '@/model/service/auth.service';
import React, { createContext, useState } from 'react'

type AuthContextType = {
    user: UserDto | null;
    signIn: (dto: SignInDto) => void;
    signOut: () => void;
    authenticated: boolean;
}

export const AuthContext = createContext<AuthContextType | null>(null);

const AuthProvider: React.FC<{children: React.ReactNode}> = ({children}) => {

    const authService = getAuthService();

    const [user, setUser] = useState<UserDto | null>(() => authService.getCurrentUser());

    const signIn = async (dto: SignInDto) => {
        const resp: UserDto = await authService.signIn(dto);
        setUser(resp);
        return resp;
    }

    const signOut = () => authService.signOut();

    const authenticated = !!authService.getCurrentUser();

  return (
    <AuthContext.Provider value={{user, signIn, signOut, authenticated}}>
        {children}
    </AuthContext.Provider>
  )
}

export default AuthProvider;