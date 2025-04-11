import { cn } from '@/lib/utils';
import { getAuthService } from '@/model/service/auth.service';
import React from 'react';
import denied from "@/assets/images/403.png";
import pageNotFound from "@/assets/images/404.png";
import { Link } from 'react-router';
import { Button } from '@/components/ui/button';

const AccessedDenied: React.FC<{three: boolean}> = ({three}) => {
    const user = getAuthService().getCurrentUser();
  return (
    <div className={cn("min-h-screen flex flex-col gap-3 justify-center items-center")}>
        <img
            src={three ? denied : pageNotFound}
            alt={three ? "403" : "404"}
            className={cn("h-1/3 w-1/3")} />
        <p className={cn("text-pretty text-center font-semibold text-md text-neutral-600")}>
            {three ? "Oops, you are not authorized to access this page." :
            "The page you requested was not found."}
        </p>
        <div className={cn("flex gap-2")}>
            <Link to={user ? `/balance/app/${user.admin ? "admin" : "user"}` : "/"}>
                <Button className={cn("w-[120px]")} variant={"outline"}>{user ? "To Dashboard" : "Home Page"}</Button>
            </Link>
            {
                !user && <Link to={"/auth/signin"}>
                    <Button className={cn("w-[120px]")}>Sign In</Button>
                </Link>
            }
        </div>
    </div>
  )
}

export default AccessedDenied;