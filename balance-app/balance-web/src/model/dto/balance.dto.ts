export interface BalanceApiPayload<T> {
    success: boolean;
    issuedAt: Date;
    payload: T;
}

export interface IconOutput {
    id: number;
    name: string;
    path: string;
}

export type IconOutputs = IconOutput[];
export interface CategoryInput {
    id: number;
    name: string;
    income: boolean;
    iconId: number;
}

export interface CategoryOutput {
    id: number;
    name: string;
    income: boolean;
    icon: IconOutput;
}

export type CategoryOutputs = CategoryOutput[];