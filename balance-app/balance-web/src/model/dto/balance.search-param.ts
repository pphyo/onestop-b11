export type CategoryIconFilter = "Income" | "Expense" | "Both";

export interface CurrencySearchParam {
    name: string;
    code: string;
}

export interface IconSearchParam {
    name: string;
    account: boolean;
    filter: CategoryIconFilter | undefined;
}

export interface CategorySearchParam {
    name: string;
    income: boolean | undefined;
}

export interface AccountSearchParam {
    name: string;
    amount: number;
}

export interface TransactionSearchParam {
    month: string;
    amount: number;
    keyword: string;
}

export interface PieChartSearchParam {
    month: string;
    income: boolean;
}