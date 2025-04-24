import { CurrencyDto, CurrencyDtos } from "@/model/dto/balance.dto";
import { CurrencySearchParam } from "@/model/dto/balance.search-param";
import { getAuthService } from "@/model/service/auth.service";
import { getCurrencyService } from "@/model/service/currency.service";
import { useEffect } from "react";
import { useState } from "react";

const useCurrency = ({name, code}: CurrencySearchParam) => {
    const currencyService = getCurrencyService();
    const admin: boolean = getAuthService().getCurrentUser()?.admin as boolean;
    const [currencies, setCurrencies] = useState<CurrencyDtos>([]);

    useEffect(() => {
        const fetchData = async () => {
            const resp = admin ? await currencyService.searchForAmin({name, code}) :
                                await currencyService.searchForUser();

            if(resp) {
                const filterWithCode = Array.from(new Map(resp.payload.map(c => [c.code, c])));
                const result = filterWithCode.map(item => item[1] as CurrencyDto);
                setCurrencies(result);
            }
        };

        fetchData();
    }, [currencyService, name, code, admin]);

    return {currencies};
}

export default useCurrency;