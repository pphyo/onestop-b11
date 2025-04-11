import { createContext, useContext } from "react";

export type Theme = {
    theme: "light" | "dark";
}

type ThemeType = {
    theme: Theme;
    setTheme: (theme: Theme) => void;
}

export const ThemeContext = createContext<ThemeType | undefined>(undefined);

export function useTheme() {
    const context = useContext(ThemeContext);
    if(!context) {
        throw new Error("useTheme must be used within ThemeProvider!");
    }
    return context;
}