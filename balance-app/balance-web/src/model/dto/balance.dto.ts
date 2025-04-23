export interface BalanceApiPayload<T> {
    success: boolean;
    issuedAt: Date;
    payload: T;
}

export interface IconDto {
    id: number;
    name: string;
    path: string;
}

export type IconDtos = IconDto[];
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
    icon: IconDto;
}

export type CategoryOutputs = CategoryOutput[];

export interface AccountOverall {
    incomeWithFormat: string;
    expenseWithFormat: string;
    balanceWithFormat: string;
}

export interface AccountDto<T = number> {
    id: number;
    name: string;
    amount: number;
    amountWithFormat?: string;
    icon: T;
}

export type AccountDtos = AccountDto[];