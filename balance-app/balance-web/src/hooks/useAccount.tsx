import { delay } from "@/lib/utils";
import { AccountDtos, AccountOverall, IconDto } from "@/model/dto/balance.dto";
import { AccountSearchParam } from "@/model/dto/balance.search-param";
import { getAccountService } from "@/model/service/account.service";
import { useEffect } from "react";
import { useCallback } from "react";
import { useState } from "react";

const useAccount = ({name, amount}: AccountSearchParam) => {
    const accountService = getAccountService();

    const [loading, setLoading] = useState(false);

    const [accounts, setAccounts] = useState<AccountDtos<IconDto>>([]);

    const [overall, setOverall] = useState<AccountOverall>({
            incomeWithFormat: "0 $",
            expenseWithFormat: "0 $",
            balanceWithFormat: "0 $"
        })

    const fetchAccounts = useCallback(async (params: AccountSearchParam) => {
        setLoading(true);
        try {
            await delay(1000);
            const resp = await accountService.search(params);

            const accountList = resp && resp.payload;

            if(accountList.length)
                setAccounts(accountList);

        } finally {
            setLoading(false);
        }
    }, [accountService]);

    useEffect(() => {
        const fetchData = () => fetchAccounts({name, amount});
        fetchData();
    }, [fetchAccounts, name, amount])

    const fetchOverall = useCallback(async () => {
        const resp = await accountService.searchOverall();
        if(resp)
            setOverall(resp.payload)
    }, [accountService]);

    useEffect(() => {
        const fetchData = () => fetchOverall();
        fetchData();
    }, [fetchOverall]);

    return {
        loading,
        accounts,
        refetchAccount: fetchAccounts,
        overall,
        refetchOverall: fetchOverall
    };
}

export default useAccount;