import React, { useState } from 'react';
import { User, UserContext } from './useUser';

export const UserProvider: React.FC<{children: React.ReactNode}> = ({children}) => {
    const [user, setUser] = useState<User>({
        name: "ပြည့်ဖြိုး",
        isLogin: true
    })

    const login = (user: User) => {
        if(user) {
            setUser({...user, isLogin: true});
        }
    };

    const logout = (user: User) => {
        if(user) {
            setUser({...user, isLogin: false});
        }
    }

  return (
    <UserContext.Provider value={{user, login, logout}}>
        { children }
    </UserContext.Provider>
  )
}

export default UserProvider;