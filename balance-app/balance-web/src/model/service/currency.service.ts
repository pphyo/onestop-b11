import { BASE_API } from "@/lib/consts.type";
import { CurrencySearchParam } from "../dto/balance.search-param";
import { BalanceApiPayload, CurrencyDtos } from "../dto/balance.dto";
import axiosInstance from "@/axios/axios-instance";

// const ADMIN_CURRENCIES_API = `${BASE_API}/admin/currencies`;
const ANY_CURRENCIES_API = `${BASE_API}/any/currencies`;

class CurrencyService {

    private static instance: CurrencyService = new CurrencyService();
    private constructor() {}

    public static getInstance(): CurrencyService {
        return CurrencyService.instance;
    }

    async searchForAmin(params: CurrencySearchParam): Promise<BalanceApiPayload<CurrencyDtos>> {
        return await axiosInstance.get(ANY_CURRENCIES_API, {params}).then(res => res.data);
    }

    async searchForUser(): Promise<BalanceApiPayload<CurrencyDtos>> {
        return await axiosInstance.get(ANY_CURRENCIES_API, {params: {name: "", code: ""}}).then(res => res.data);
    }

}

export const getCurrencyService = () => CurrencyService.getInstance();