import { create } from "zustand";

export interface BalanceErrorPayload {
    errorAt: Date;
    title: string;
    messages: string[];
}

type ErrorType = {
    error: BalanceErrorPayload | null;
    setError: (error: BalanceErrorPayload | null) => void;
}

export const useErrorStore = create<ErrorType>((set) => ({
    error: null,
    setError: (error) => set({error})
}));