import { delay } from "@/lib/utils";
import { TransactionForMonthly } from "@/model/dto/balance.dto";
import { TransactionSearchParam } from "@/model/dto/balance.search-param";
import { getTransactionService } from "@/model/service/transaction.service";
import { useCallback, useEffect, useState } from "react";

const useTransaction = ({month, amount, keyword}: TransactionSearchParam) => {
    const transactionService = getTransactionService();
    const [transactions, setTransactions] = useState<TransactionForMonthly>();
    const [loading, setLoading] = useState(false);

    const fetchTransactions = useCallback(async (params: TransactionSearchParam) => {
        setLoading(true);
        try {
            await delay(1000);
            const resp = await transactionService.search(params);
            const payload = resp && resp.payload;

            if(payload.dailyTransactions.length) {
                setTransactions(payload);
            } else {
                setTransactions(undefined);
            }
        } finally {
            setLoading(false);
        }
    }, [transactionService]);

    useEffect(() => {
        const fetchData = () => fetchTransactions({month, amount, keyword});
        fetchData();
    }, [fetchTransactions, month, amount, keyword])

    return {loading, transactions, refetch: fetchTransactions};
}

export default useTransaction;