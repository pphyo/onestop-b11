import React from 'react'
import { Tooltip, TooltipContent, TooltipProvider } from '@/components/ui/tooltip'
import { TooltipTrigger } from '@radix-ui/react-tooltip';

type BalanceTooltipProps = {
    children: React.ReactNode;
    content: string;
}

export const BalanceTooltip: React.FC<BalanceTooltipProps> = ({children, content}) => {
  return (
    <TooltipProvider>
        <Tooltip>
            <TooltipTrigger asChild>{children}</TooltipTrigger>
            <TooltipContent>
                <p>{content}</p>
            </TooltipContent>
        </Tooltip>
    </TooltipProvider>
  )
}
