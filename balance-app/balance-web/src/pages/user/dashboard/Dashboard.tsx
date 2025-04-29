import MainPageTitle from "@/components/widget/MainPageTitle";
import useDashboard from "@/hooks/useDashboard";
import MainPageLayout from "@/layouts/MainPageLayout";
import { cn } from "@/lib/utils";
import { format } from "date-fns";
import { LayoutDashboard } from "lucide-react";
import { useMemo, useState } from "react";

const Dashboard = () => {
  const [incomeDateParam, setIncomeDateParam] = useState(new Date());
  const [expenseDateParam, setExpenseDateParam] = useState(new Date());

  const incomePieChartParams = useMemo(() => ({
    month: format(incomeDateParam, "yyyy-MM"),
    income: true
  }), [incomeDateParam]);

  const expensePieChartParams = useMemo(() => ({
    month: format(expenseDateParam, "yyyy-MM"),
    income: true
  }), [expenseDateParam])

  const {incomeData, refetchIncome} = useDashboard({incomeMonth: incomePieChartParams.month, expenseMonth: expensePieChartParams.month, income: incomePieChartParams.income});
  const {expenseData, refetchExpense} = useDashboard({incomeMonth: incomePieChartParams.month, expenseMonth: expensePieChartParams.month, income: expensePieChartParams.income});

  return (
    <MainPageLayout>
      <MainPageTitle title="Dashboard" icon={LayoutDashboard} />

      <div className={cn("grid md:grid-cols-2 sm:grid-cols-1 gap-6")}>

        {/* income pie chart */}
        

      </div>

    </MainPageLayout>
  )
}

export default Dashboard;