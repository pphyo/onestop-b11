import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import BalancePopover from "@/components/widget/BalancePopover";
import BalanceSearchFormControl from "@/components/widget/BalanceSearchFormControl";
import MainPageTitle from "@/components/widget/MainPageTitle";
import MainPageLayout from "@/layouts/MainPageLayout";
import { cn } from "@/lib/utils";
import { Funnel, Plus, Wallet } from "lucide-react";

const Account = () => {

  // const accountService = getAccountService();

  return (
    <>
      <MainPageLayout>
        <MainPageTitle title="Your Accounts" icon={Wallet} />

        { /* overall */ }
        <div className={cn("flex flex-col gap-4 bg-gray-100 rounded-md p-4")}>
          <h4 className={cn("text-xl text-pretty text-neutral-700 font-bold")}>
            Account Overall
          </h4>

          <div className={cn("flex gap-4 items-center")}>
            <div className={cn("flex-grow p-4 rounded-md shadow-md bg-white")}>
              <h4 className={cn("text-pretty text-neutral-500 font-bold")}>Income</h4>
              <p className={cn("text-2xl font-semibold text-neutral-700")}>200,000 $</p>
            </div>

            <div className={cn("flex-grow p-4 rounded-md shadow-md bg-white")}>
              <h4 className={cn("text-pretty text-neutral-500 font-bold")}>Expense</h4>
              <p className={cn("text-2xl font-semibold text-neutral-700")}>14,000 $</p>
            </div>

            <div className={cn("flex-grow p-4 rounded-md shadow-md bg-white")}>
              <h4 className={cn("text-pretty text-neutral-500 font-bold")}>Balance</h4>
              <p className={cn("text-2xl font-semibold text-neutral-700")}>186,000 $</p>
            </div>
          </div>
        </div>

        { /* control */}
        <div className={cn("flex justify-between")}>
          <Button variant={"outline"}>
            <Plus /> Create Account
          </Button>

          <div className={cn("flex justify-between gap-2")}>
            <BalancePopover trigger={<Button variant={"ghost"}><Funnel /> 2 Filters</Button>}>
              <BalanceSearchFormControl label="Name" labelFor="name">
                <Input type="text" id="name" placeholder="Account name" />
              </BalanceSearchFormControl>

              <BalanceSearchFormControl label="Amount" labelFor="amount">
                <Input type="number" id="amount" value="0" />
              </BalanceSearchFormControl>
            </BalancePopover>
          </div>
        </div>

      </MainPageLayout>

      {}
    </>
  )
}

export default Account;
