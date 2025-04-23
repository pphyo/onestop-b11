import { BASE_API } from "@/lib/consts.type";
import { AccountDto, AccountDtos, AccountOverall, BalanceApiPayload } from "../dto/balance.dto";
import axiosInstance from "@/axios/axios-instance";
import { AccountSearchParam } from "../dto/balance.search-param";

const ACCOUNTS_API = `${BASE_API}/user/accounts`

class AccountService {
    private static instance: AccountService = new AccountService();

    private constructor() {}

    public static getInstance(): AccountService {
        return AccountService.instance;
    }

    async save(data: AccountDto): Promise<AccountDto> {
        const {id, ...value} = data;
        return id == 0 ?
            await axiosInstance.post(ACCOUNTS_API, value).then(res => res.data) :
            await axiosInstance.post(`${ACCOUNTS_API}/${id}`, data);
    }

    async delete(id: number): Promise<boolean> {
        return await axiosInstance.delete(`${ACCOUNTS_API}/${id}`);
    }

    async search(params: AccountSearchParam): Promise<BalanceApiPayload<AccountDtos>> {
        return await axiosInstance.get(ACCOUNTS_API, {params});
    }

    async searchById(id: number): Promise<BalanceApiPayload<AccountDto>> {
        return await axiosInstance.get(`${ACCOUNTS_API}/${id}`);
    }

    async searchOverall(): Promise<BalanceApiPayload<AccountOverall>> {
        return await axiosInstance.get(`${ACCOUNTS_API}/overall`);
    }

}

export const getAccountService = () => AccountService.getInstance();