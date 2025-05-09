import { BASE_API } from "@/lib/consts.type";
import { AccountDto, AccountDtos, AccountOverall, BalanceApiPayload, IconDto } from "../dto/balance.dto";
import axiosInstance from "@/axios/axios-instance";
import { AccountSearchParam } from "../dto/balance.search-param";

const ACCOUNTS_API = `${BASE_API}/user/accounts`

class AccountService {
    private static instance: AccountService = new AccountService();

    private constructor() {}

    public static getInstance(): AccountService {
        return AccountService.instance;
    }

    async save(data: AccountDto<number>): Promise<AccountDto> {
        const {id, ...value} = data;
        return id == 0 ?
            await axiosInstance.post(ACCOUNTS_API, value).then(res => res.data) :
            await axiosInstance.put(`${ACCOUNTS_API}/${id}`, data).then(res => res.data);
    }

    async delete(id: number): Promise<boolean> {
        return await axiosInstance.delete(`${ACCOUNTS_API}/${id}`).then(res => res.data);
    }

    async search(params: AccountSearchParam): Promise<BalanceApiPayload<AccountDtos<IconDto>>> {
        return await axiosInstance.get(ACCOUNTS_API, {params}).then(res => res.data);
    }

    async searchById(id: number): Promise<BalanceApiPayload<AccountDto>> {
        return await axiosInstance.get(`${ACCOUNTS_API}/${id}`).then(res => res.data);
    }

    async searchOverall(): Promise<BalanceApiPayload<AccountOverall>> {
        return await axiosInstance.get(`${ACCOUNTS_API}/overall`).then(res => res.data);
    }

}

export const getAccountService = () => AccountService.getInstance();