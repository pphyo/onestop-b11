import BalanceErrorAlert from "@/error/BalanceErrorAlert"
import { cn } from "@/lib/utils"
import { Outlet } from "react-router"

export const PublicLayout = () => {
  return (
    <main className={cn("min-h-screen flex justify-center items-center")}>
        <Outlet></Outlet>
        <BalanceErrorAlert />
    </main>
  )
}
