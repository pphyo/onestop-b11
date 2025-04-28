export interface BalanceApiPayload<T> {
    success: boolean;
    issuedAt: Date;
    payload: T;
}

export interface CurrencyDto {
    id: number;
    country: string;
    name: string;
    code: string;
}

export type CurrencyDtos = CurrencyDto[];

export type DecimalPlace = "ZERO" | "ONE" | "TWO";
export type CurrencyPosition = "BEFORE" | "AFTER" | "NONE";
export interface SettingDto<T = number> {
    id: number;
    decimalPlace: DecimalPlace;
    currencyPosition: CurrencyPosition;
    currency: T;
}

export interface IconDto {
    id: number;
    name: string;
    path: string;
}

export type IconDtos = IconDto[];
export interface CategoryDto<T = number> {
    id: number;
    name: string;
    income: boolean;
    icon: T;
}

export type CategoryDtos = CategoryDto<IconDto>[];

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

export type AccountDtos<T> = AccountDto<T>[];

export type TransactionType = "Income" | "Expense" | "Transfer"

interface TransactionBaseDto {
    id: number;
    amount: number;
    amountWithFormat?: string;
    note: string;
    issuedAt?: string;
}

export type TransactionDto<T = number, P = number> = {
    type: "Income" | "Expense";
    category: T;
    account: P;
} & TransactionBaseDto | {
    type: "Transfer";
    accountFrom: T;
    accountTo: P;
} & TransactionBaseDto;

export type TransactionForDaily = {
    date: string;
    transactions: TransactionDto<AccountDto<IconDto>, AccountDto<IconDto> | CategoryDto<IconDto>>[];
}

export type TransactionForMonthly = {
    month: string;
    dailyTransactions: TransactionForDaily[]
}