import { BASE_API } from "@/lib/consts.type";
import { IconSearchParam } from "../dto/balance.search-param";
import { BalanceApiPayload, IconDto, IconDtos } from "../dto/balance.dto";
import axiosInstance from "@/axios/axios-instance";

const ICONS_API = `${BASE_API}/user/icons`;

class IconService {
    private static instance: IconService;

    private constructor() {}

    public static getInstance(): IconService {
        if(!IconService.instance) {
            IconService.instance = new IconService();
        }
        return IconService.instance;
    }

    async search(params: IconSearchParam): Promise<BalanceApiPayload<IconDtos>> {
        return await axiosInstance.get(ICONS_API, {params}).then(res => res.data);
    }

    async searchById(id: number): Promise<BalanceApiPayload<IconDto>> {
        return await axiosInstance(`${ICONS_API}/${id}`).then(res => res.data);
    }
}

export const getIconService = () => IconService.getInstance();