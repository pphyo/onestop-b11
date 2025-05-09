import AccessedDenied from "@/components/widget/AccessedDenied";
import AppLayout from "@/layouts/AppLayout";
import { PublicLayout } from "@/layouts/PublicLayout";
import { SignIn } from "@/pages/public/auth/SignIn";
import { SignUp } from "@/pages/public/auth/SignUp";
import { Home } from "@/pages/public/Home";
import Account from "@/pages/user/account/Account";
import AccountDetail from "@/pages/user/account/AccountDetail";
import Budget from "@/pages/user/budget/Budget";
import Category from "@/pages/user/category/Category";
import CategoryDetail from "@/pages/user/category/CategoryDetail";
import Dashboard from "@/pages/user/dashboard/Dashboard";
import Setting from "@/pages/user/setting/Setting";
import SetUpWizard from "@/pages/user/SetUpWizard";
import Transaction from "@/pages/user/transaction/Transaction";
import { Navigate, RouteObject } from "react-router";

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
    },
    {path: "/balance/app", children: [
        { path: "setup", element: <SetUpWizard /> },

        { path: "user", element: <AppLayout />, children: [
            { path: "dashboard", element: <Dashboard /> },
            { path: "transaction", element: <Transaction /> },
            { path: "budget", element: <Budget /> },
            { path: "account", element: <Account /> },
            { path: "account/:id", element: <AccountDetail /> },
            { path: "category", element: <Category /> },
            { path: "category/:id", element: <CategoryDetail /> },
            { path: "setting", element: <Setting /> },
            { index: true, element: <Navigate to={"/balance/app/user/dashboard"} />}
        ]},
    ]},
    { path: "*", element: <AccessedDenied three={false} /> }
]