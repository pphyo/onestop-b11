import { PieChartDtos } from "@/model/dto/balance.dto";
import { PieChartSearchParam } from "@/model/dto/balance.search-param";
import { getDashboardService } from "@/model/service/dashboard.service";
import { useCallback, useEffect, useState } from "react";

const useDashboard = ({incomeMonth, expenseMonth, income}: {incomeMonth: string, expenseMonth: string, income: boolean}) => {

    const dashboardService = getDashboardService();

    const [incomeData, setIncomeData] = useState<PieChartDtos>([]);
    const [expenseData, setExpenseData] = useState<PieChartDtos>([]);

    const fetchIncomePieChartData = useCallback(async (params: PieChartSearchParam) => {
        const resp = await dashboardService.searchPieChartData(params);
        if(resp.payload && resp.payload.length > 0) {
            setIncomeData(resp.payload);
        } else {
            setIncomeData([]);
        }
    }, [dashboardService]);

    useEffect(() => {
        const fetchData = () => fetchIncomePieChartData({month: incomeMonth, income});
        fetchData();
    }, [fetchIncomePieChartData, incomeMonth, income]);

    const fetchExpensePieChartData = useCallback(async (params: PieChartSearchParam) => {
        const resp = await dashboardService.searchPieChartData(params);
        if(resp.payload && resp.payload.length > 0) {
            setExpenseData(resp.payload);
        } else {
            setExpenseData([]);
        }
    }, [dashboardService]);

    useEffect(() => {
        const fetchData = () => fetchExpensePieChartData({month: expenseMonth, income});
        fetchData();
    }, [fetchExpensePieChartData, expenseMonth, income]);

    return {incomeData, refetchIncome: fetchIncomePieChartData, expenseData, refetchExpense: fetchExpensePieChartData};
}

export default useDashboard;