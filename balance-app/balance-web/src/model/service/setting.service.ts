import { BASE_API } from "@/lib/consts.type";
import { BalanceApiPayload, CurrencyDto, SettingDto } from "../dto/balance.dto";
import axiosInstance from "@/axios/axios-instance";

const SETTINGS_API = `${BASE_API}/user/settings`;

class SettingService {

    private static instance: SettingService = new SettingService();
    private constructor() {}

    public static getInstance(): SettingService {
        return SettingService.instance;
    }

    async save(data: SettingDto): Promise<SettingDto<CurrencyDto>> {
        const {id, ...value} = data;
        return id === 0 ?
                await axiosInstance.post(SETTINGS_API, value).then(res => res.data) :
                await axiosInstance.put(`${SETTINGS_API}/${id}`, value).then(res => res.data);
    }

    async searchCurrent(): Promise<BalanceApiPayload<SettingDto<CurrencyDto>>> {
        return await axiosInstance.get(`${SETTINGS_API}/current`).then(res => res.data);
    }

}

export const getSettingService = () => SettingService.getInstance();