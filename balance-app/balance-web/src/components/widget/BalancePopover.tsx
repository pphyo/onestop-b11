import React from 'react'
import { Popover, PopoverContent, PopoverTrigger } from '@/components/ui/popover';
import { cn } from '@/lib/utils';

type BalancePopoverProps = {
    trigger: React.ReactNode;
    children: React.ReactNode;
}

const BalancePopover: React.FC<BalancePopoverProps> = ({trigger, children}) => {
  return (
    <Popover>
        <PopoverTrigger asChild>
            {trigger}
        </PopoverTrigger>
        <PopoverContent side="left" className={cn("w-[250px] flex flex-col gap-4")}>
            {children}
        </PopoverContent>
    </Popover>
  )
}

export default BalancePopover;