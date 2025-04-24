export type CategoryIconFilter = "Income" | "Expense" | "Both";

export interface IconSearchParam {
    name: string;
    account: boolean;
    filter: CategoryIconFilter;
}

export interface CategorySearchParam {
    name: string;
    income: boolean | undefined;
}

export interface AccountSearchParam {
    name: string;
    amount: number;
}