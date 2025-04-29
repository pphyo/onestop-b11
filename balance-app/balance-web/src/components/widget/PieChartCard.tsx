import React from 'react'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card';
import { cn } from '@/lib/utils';
import { format } from 'date-fns';
import { ChartConfig, ChartContainer, ChartLegend, ChartLegendContent, ChartTooltip, ChartTooltipContent } from '@/components/ui/chart';
import { Pie, PieChart } from 'recharts';
import { PieChartDtos } from '@/model/dto/balance.dto';

type PieChartCardProps = {
    income: boolean;
    month: string;
    config: ChartConfig;
    data: PieChartDtos;
};

const PieChartCard: React.FC<PieChartCardProps> = ({income, month, config, data}) => {
  return (
    <Card className={cn("flex flex-col")}>
        <CardHeader className={cn("items-center pb-0")}>
            <CardTitle>Chart for {income ? "Income" : "Expense"}</CardTitle>
            <CardDescription>{ format(month, "MMMM yyyy") }</CardDescription>
        </CardHeader>

        <CardContent className={cn("flex-1 pb-0")}>
            <ChartContainer config={config} className={cn("mx-auto aspect-square max-h-[250px]")}>

                <PieChart>
                    <ChartTooltip cursor={false} content={<ChartTooltipContent hideLabel />} />
                    <Pie
                        data={data}
                        dataKey="amount"
                        nameKey="name"
                        innerRadius={60}
                    />

                    <ChartLegend
                        content={<ChartLegendContent nameKey="name" />}
                        className="-translate-y-2 flex-wrap gap-2 [&>*]:basis-1/4 [&>*]:justify-center"
                    />
                </PieChart>

            </ChartContainer>
        </CardContent>

        <CardFooter></CardFooter>
    </Card>
  )
}

export default PieChartCard;