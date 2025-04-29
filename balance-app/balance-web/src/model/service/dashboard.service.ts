import { BASE_API } from "@/lib/consts.type";
import { PieChartSearchParam } from "../dto/balance.search-param";
import { BalanceApiPayload, PieChartDtos } from "../dto/balance.dto";
import axiosInstance from "@/axios/axios-instance";

const DASHBOARD_API = `${BASE_API}/user/dashboards`

class DashboardService {
    private static instance: DashboardService = new DashboardService();

    private constructor() {}

    public static getInstance(): DashboardService {
        return DashboardService.instance;
    }

    async searchPieChartData(params: PieChartSearchParam): Promise<BalanceApiPayload<PieChartDtos>> {
        return axiosInstance.get(DASHBOARD_API, {params}).then(res => res.data);
    }
}

export const getDashboardService = () => DashboardService.getInstance();