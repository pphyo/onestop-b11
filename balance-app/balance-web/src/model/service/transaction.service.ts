import { BASE_API } from "@/lib/consts.type";
import { AccountDto, BalanceApiPayload, CategoryDto, IconDto, TransactionDto, TransactionForMonthly } from "../dto/balance.dto";
import axiosInstance from "@/axios/axios-instance";
import { TransactionSearchParam } from "../dto/balance.search-param";

const TRANSACTIONS_API = `${BASE_API}/user/transactions`

class TransactionService {
    private static instance: TransactionService = new TransactionService();

    private constructor() {}

    public static getInstance(): TransactionService {
        return TransactionService.instance;
    }

    async save(data: TransactionDto): Promise<TransactionDto<AccountDto<IconDto>, AccountDto<IconDto> | CategoryDto<IconDto>>> {
        const {id, ...value} = data;
        return id === 0 ?
            await axiosInstance.post(TRANSACTIONS_API, value)
                               .then(res => res.data) :
            await axiosInstance.put(`${TRANSACTIONS_API}/${id}`, data)
                               .then(res => res.data);
    }

    async delete(id: number): Promise<boolean> {
        return axiosInstance.delete(`${TRANSACTIONS_API}/${id}`).then(res => res.data);
    }

    async search(params: TransactionSearchParam): Promise<BalanceApiPayload<TransactionForMonthly>> {
        return await axiosInstance.get(TRANSACTIONS_API, {params})
                                  .then(res => res.data);
    }
}

export const getTransactionService = () => TransactionService.getInstance();