import { Calculator, Euro, LayoutDashboard, LucideIcon, NotepadText, Shell, Tags, UsersRound, Wallet } from "lucide-react";

export const BASE_API = "http://localhost:8080/balance/api/v1";
type BASE_API_PREFIX = typeof BASE_API;
export type ENDPOINT = `${BASE_API_PREFIX}/${string}`;
export type MENU = {
    label: string;
    tooltip: string;
    url: string;
    icon: LucideIcon;
}

export const HEADER_JSON = {
    "Content-Type": "application/json"
}

export const USER_MENUS: MENU[] = [
    {
        label: "Dashboard",
        tooltip: "Balance Dashboard",
        url: "/balance/app/user/dashboard",
        icon: LayoutDashboard
    },
    {
        label: "Transaction",
        tooltip: "Transaction Management",
        url: "/balance/app/user/transaction",
        icon: NotepadText
    },
    {
        label: "Budget",
        tooltip: "Budget Management",
        url: "/balance/app/user/budget",
        icon: Calculator
    },
    {
        label: "Account",
        tooltip: "Account Management",
        url: "/balance/app/user/account",
        icon: Wallet
    },
    {
        label: "Category",
        tooltip: "Category Management",
        url: "/balance/app/user/category",
        icon: Tags
    }
];

export const ADMIN_MENUS: MENU[] = [
    {
        label: "User",
        tooltip: "User Management",
        url: "/balance/app/admin/user",
        icon: UsersRound
    },
    {
        label: "Currency",
        tooltip: "Currency Management",
        url: "/balance/app/admin/currency",
        icon: Euro
    },
    {
        label: "Icon",
        tooltip: "Icon Management",
        url: "/balance/app/admin/icon",
        icon: Shell
    }
];