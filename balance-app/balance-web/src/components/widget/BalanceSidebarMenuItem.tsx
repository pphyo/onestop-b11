import { MENU } from '@/lib/consts.type';
import React from 'react';
import { BalanceTooltip } from './BalanceTooltip';
import { SidebarMenuButton, SidebarMenuItem } from '../ui/sidebar';
import { Link, useLocation } from 'react-router';
import { cn } from '@/lib/utils';

export const BalanceSidebarMenuItem: React.FC<{menu: MENU}> = ({menu}) => {
  const location = useLocation();
  return (
    <BalanceTooltip content={menu.tooltip}>
        <SidebarMenuItem>
            <SidebarMenuButton isActive={menu.url === location.pathname} asChild className={cn("h-10")}>
                <Link to={menu.url}>
                    {<menu.icon />}
                    {menu.label}
                </Link>
            </SidebarMenuButton>
        </SidebarMenuItem>
    </BalanceTooltip>
  )
}
