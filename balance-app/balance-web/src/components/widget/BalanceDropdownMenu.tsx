import React from 'react'
import { DropdownMenu, DropdownMenuContent, DropdownMenuTrigger } from '@/components/ui/dropdown-menu';

type BalanceDropdownMenuProps = {
    trigger: React.ReactNode;
    children: React.ReactNode;
}

const BalanceDropdownMenu: React.FC<BalanceDropdownMenuProps> = ({trigger, children}) => {
  return (
    <DropdownMenu>
        <DropdownMenuTrigger asChild>
            {trigger}
        </DropdownMenuTrigger>
        <DropdownMenuContent side="left">
            {children}
        </DropdownMenuContent>
    </DropdownMenu>
  )
}

export default BalanceDropdownMenu;