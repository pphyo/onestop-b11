interface BalanceApiPayload<T> {
    success: boolean;
    issuedAt: Date;
    payload: T;
}

export type { BalanceApiPayload };