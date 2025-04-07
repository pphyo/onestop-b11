import { cn } from "@/lib/utils"
import { Outlet } from "react-router"

export const PublicLayout = () => {
  return (
    <main className={cn("min-h-screen")}>
        <Outlet></Outlet>
    </main>
  )
}
