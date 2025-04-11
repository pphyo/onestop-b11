import { SidebarMenu, SidebarProvider } from "@/components/ui/sidebar";
import BalanceSidebar from "@/components/widget/BalanceSidebar";
import { BalanceSidebarMenuItem } from "@/components/widget/BalanceSidebarMenuItem";
import { ADMIN_MENUS, USER_MENUS } from "@/lib/consts.type";
import { cn } from "@/lib/utils";
import { getAuthService } from "@/model/service/auth.service";
import AuthenticatedRoute from "@/routes/AuthenticatedRoute";
import { HandCoins } from "lucide-react";
import { Outlet } from "react-router";

const AppLayout = () => {
    const authService = getAuthService();
    const user = authService.getCurrentUser();
    const title = <span className={cn("text-lg font-bold")}>Balance User</span>;
    const content = <SidebarMenu>
        {
            user?.admin ?
            ADMIN_MENUS.map(menu => <BalanceSidebarMenuItem key={menu.url} menu={menu} />) :
            USER_MENUS.map(menu => <BalanceSidebarMenuItem key={menu.url} menu={menu} />)
        }
    </SidebarMenu>

  return (
    <AuthenticatedRoute requiredAdmin={user?.admin as boolean}>
        <SidebarProvider>
            <BalanceSidebar logo={HandCoins} title={title} content={content} />
            <main className={cn("p-4 w-full")}>
                <Outlet />
            </main>
        </SidebarProvider>
    </AuthenticatedRoute>
  )
}

export default AppLayout;