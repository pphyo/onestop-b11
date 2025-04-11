import React from 'react';
import {useState} from 'react';
import {Theme, ThemeContext} from './useTheme';

export const ThemeProvider: React.FC<{children: React.ReactNode}> = ({children}) => {
    const [theme, setTheme] = useState<Theme>({theme: "light"});

    const toggleTheme = (theme: Theme) => {
        setTheme(theme.theme === "light" ? {theme: "dark"} : {theme: "light"});
    };

    return (
        <ThemeContext.Provider value={{theme, setTheme: toggleTheme}}>
            {children}
        </ThemeContext.Provider>
    )
}
export default ThemeProvider