import React from 'react'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card';
import { cn } from '@/lib/utils';

type BalanceCardProps = {
    cardSize: string;
    cardTitle: string;
    cardDescription: string;
    content: React.ReactNode;
    footer: React.ReactNode;
}

export const BalanceCard: React.FC<BalanceCardProps> = ({cardSize, cardTitle, cardDescription, content, footer}) => {
  return (
    <Card className={cardSize}>
        <CardHeader className={cn("text-center")}>
            <CardTitle className={cn("text-xl leading-none")}>{cardTitle}</CardTitle>
            <CardDescription className={cn("leading-none")}>{cardDescription}</CardDescription>
        </CardHeader>

        <CardContent className={cn("flex flex-col gap-4")}>{content}</CardContent>
        <CardFooter>{footer}</CardFooter>
    </Card>
  )
}
