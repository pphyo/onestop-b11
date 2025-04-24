import React from 'react'
import { Popover, PopoverContent, PopoverTrigger } from '@/components/ui/popover';
import { cn } from '@/lib/utils';
import { MenuSide } from '@/lib/consts.type';

type BalancePopoverProps = {
    trigger: React.ReactNode;
    children: React.ReactNode;
    open?: boolean;
    onOpenChange?: (open: boolean) => void;
    side: MenuSide
}

const BalancePopover: React.FC<BalancePopoverProps> = ({trigger, children, open, onOpenChange, side}) => {
  return (
    <Popover open={open} onOpenChange={onOpenChange}>
        <PopoverTrigger asChild>
            {trigger}
        </PopoverTrigger>
        <PopoverContent side={side} className={cn("w-[250px] flex flex-col gap-4")}>
            {children}
        </PopoverContent>
    </Popover>
  )
}

export default BalancePopover;