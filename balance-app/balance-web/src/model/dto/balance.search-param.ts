export type IconFilterType = "Income" | "Expense" | "Both";

export interface IconSearchParam {
    name: string;
    account: boolean;
    filter: IconFilterType;
}

export interface CategorySearchParam {
    name: string;
    income: boolean | undefined;
}