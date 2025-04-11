import React, { useState } from 'react';
import { Sidebar, SidebarContent, SidebarFooter, SidebarHeader, SidebarMenu, SidebarMenuButton, SidebarMenuItem } from '@/components/ui/sidebar';
import { cn } from '@/lib/utils';
import { ChevronUp, HeartCrack, LogOut, LucideIcon, Settings2, User2 } from 'lucide-react';
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from '@/components/ui/dropdown-menu';
import { getAuthService } from '@/model/service/auth.service';
import { useNavigate } from 'react-router';
import BalanceAlertDialog from './BalanceAlertDialog';
import { useAuth } from '@/hooks/useAuth';

type BalanceSidebarProps = {
    logo: LucideIcon;
    title: React.ReactNode;
    content: React.ReactNode;
}

const BalanceSidebar: React.FC<BalanceSidebarProps> = (props) => {
    const authService = getAuthService();
    const user = authService.getCurrentUser();
    const { signOut } = useAuth();

    const [alertOpen, setAlertOpen] = useState(false);
    const navigate = useNavigate();

    const handleSignOut = () => {
        signOut();
        navigate("/auth/signin");
    };

  return (
    <>
        <Sidebar>
            <SidebarHeader className={cn("flex flex-row gap-4 justify-center items-center")}>
                {<props.logo size={36} />} <p>{props.title}</p>
            </SidebarHeader>

            <SidebarContent>
                {props.content}
            </SidebarContent>

            <SidebarFooter>
                <SidebarMenu>
                    <SidebarMenuItem>
                        <DropdownMenu>
                            <DropdownMenuTrigger asChild>
                                <SidebarMenuButton>
                                    <User2 /> {user?.name || "User"}
                                    <ChevronUp className={cn("ml-auto")} />
                                </SidebarMenuButton>
                            </DropdownMenuTrigger>

                            <DropdownMenuContent side="top" className="w-[239px]">
                                <DropdownMenuItem className={cn("flex justify-between hover:cursor-pointer")} onClick={() => navigate("/balance/app/user/setting")}>
                                    <span>Setting</span>
                                    <Settings2 />
                                </DropdownMenuItem>
                                <DropdownMenuItem onClick={() => setAlertOpen(true)} className={cn("flex justify-between hover:cursor-pointer")}>
                                    <span>Sign Out</span>
                                    <LogOut />
                                </DropdownMenuItem>
                            </DropdownMenuContent>
                        </DropdownMenu>
                    </SidebarMenuItem>
                </SidebarMenu>
            </SidebarFooter>
        </Sidebar>

        <BalanceAlertDialog
            title="Sign Out Confirm"
            open={alertOpen}
            onClose={() => setAlertOpen(false)}
            onAction={handleSignOut}
            actionText="Sign Out"
        >
            <div className={cn("flex gap-2")}>
                Are you sure to sign out? <HeartCrack />
            </div>
        </BalanceAlertDialog>
    </>
  )
}

export default BalanceSidebar;
