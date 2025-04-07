import { PublicLayout } from "@/layouts/PublicLayout";
import { SignIn } from "@/pages/public/auth/SignIn";
import { SignUp } from "@/pages/public/auth/SignUp";
import { Home } from "@/pages/public/Home";
import { RouteObject } from "react-router";

type RouteObjects = RouteObject[];

export const BALANCE_ROUTES: RouteObjects = [
    {
        path: "/",
        element: <PublicLayout />,
        children: [
            { index: true, element: <Home /> },
            { path: "auth/signin", element: <SignIn /> },
            { path: "auth/signup", element: <SignUp /> }
        ]
    }
]