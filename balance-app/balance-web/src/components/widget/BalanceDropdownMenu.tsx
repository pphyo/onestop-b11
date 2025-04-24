import React from 'react'
import { DropdownMenu, DropdownMenuContent, DropdownMenuTrigger } from '@/components/ui/dropdown-menu';
import { MenuSide } from '@/lib/consts.type';

type BalanceDropdownMenuProps = {
    trigger: React.ReactNode;
    children: React.ReactNode;
    open?: boolean;
    onOpenChange?: (open: boolean) => void;
    side: MenuSide;
}

const BalanceDropdownMenu: React.FC<BalanceDropdownMenuProps> = ({trigger, children, open, onOpenChange, side}) => {
  return (
    <DropdownMenu open={open} onOpenChange={onOpenChange}>
        <DropdownMenuTrigger asChild>
            {trigger}
        </DropdownMenuTrigger>
        <DropdownMenuContent side={side}>
            {children}
        </DropdownMenuContent>
    </DropdownMenu>
  )
}

export default BalanceDropdownMenu;