import { Button } from "@/components/ui/button";
import { DropdownMenuItem } from "@/components/ui/dropdown-menu";
import { Input } from "@/components/ui/input";
import BalancePopover from "@/components/widget/BalancePopover";
import BalanceSearchFormControl from "@/components/widget/BalanceSearchFormControl";
import DataBox from "@/components/widget/DataBox";
import DataBoxContainer from "@/components/widget/DataBoxContainer";
import DataNotFound from "@/components/widget/DataNotFound";
import Loading from "@/components/widget/Loading";
import MainPageTitle from "@/components/widget/MainPageTitle";
import useAccount from "@/hooks/useAccount";
import MainPageLayout from "@/layouts/MainPageLayout";
import { cn } from "@/lib/utils";
import { getAccountService } from "@/model/service/account.service";
import { Funnel, Plus, Wallet } from "lucide-react";
import { z } from "zod";
import { iconFormSchema } from "../category/Category";
import { DefaultValues, useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { AccountDto, IconDto } from "@/model/dto/balance.dto";
import AccountForm from "./AccountForm";
import { useState } from "react";
import BalanceAlertDialog from "@/components/widget/BalanceAlertDialog";

const accountFormSchema = z.object({
  id: z.number(),
  name: z.string().min(1, {message: "Account name required."}),
  amount: z.coerce.number().gt(0, {message: "Amount must be positive."}),
  icon: iconFormSchema
});

export type AccountFormData = z.infer<typeof accountFormSchema>;

const Account = () => {
  const accountDefaultValues: DefaultValues<AccountDto<IconDto>> = {
    id: 0,
    name: "",
    amount: 0,
    icon: {id: 0, name: "", path: ""}
  }

  const accountService = getAccountService();

  const [openAccountForm, setOpenAccountForm] = useState(false);

  const [openAlert, setOpenAlert] = useState(false);

  const [idForDelete, setIdForDelete] = useState(0);

  const {loading, accounts, overall, refetchAccount, refetchOverall} = useAccount({name: "", amount: 0});

  const form = useForm<AccountFormData>({
    resolver: zodResolver(accountFormSchema),
    defaultValues: accountDefaultValues
  })

  const handleOpenAccountForm = () => {
    resetForm();
    setOpenAccountForm(true);
  };

  const handleSubmitAccountForm = async (data: AccountFormData) => {
    const resp = await accountService.save({
      id: data.id,
      name: data.name,
      amount: data.amount,
      icon: data.icon.id
    });

    if(resp) {
      refetchAccount({name: "", amount: 0});
      refetchOverall();
    }
    setOpenAccountForm(false);
  };

  const handleEdit = async (data: AccountDto<IconDto>) => {
    resetForm();
    form.setValue("id", data.id);
    form.setValue("name", data.name);
    form.setValue("amount", data.amount);
    form.setValue("icon", data.icon);
    setOpenAccountForm(true);
  }

  const handleDelete = async () => {
    const resp = await accountService.delete(idForDelete);
    if(resp) {
      refetchAccount({name: "", amount: 0});
      refetchOverall();
    }
    setOpenAlert(false);
  };

  const handleConfirm = (id: number) => {
    setIdForDelete(id);
    setOpenAlert(true);
  };

  const resetForm = () => {
    form.reset();
    form.setValue("id", 0);
    form.setValue("name", "");
    form.setValue("amount", 0);
    form.setValue("icon", {id: 0, name: "", path: ""});
  };

  return (
    <>
      <MainPageLayout>
        <MainPageTitle title="Accounts" icon={Wallet} />

        { /* overall */ }
        <div className={cn("flex flex-col gap-4 bg-gray-100 rounded-md p-4")}>
          <h4 className={cn("text-xl text-pretty text-neutral-700 font-bold")}>
            Account Overall
          </h4>

          <div className={cn("flex gap-4 items-center")}>
            <div className={cn("flex-grow p-4 rounded-md shadow-md bg-white")}>
              <h4 className={cn("text-pretty text-neutral-500 font-bold")}>Income</h4>
              <p className={cn("text-2xl font-semibold text-neutral-700")}>{ overall.incomeWithFormat }</p>
            </div>

            <div className={cn("flex-grow p-4 rounded-md shadow-md bg-white")}>
              <h4 className={cn("text-pretty text-neutral-500 font-bold")}>Expense</h4>
              <p className={cn("text-2xl font-semibold text-neutral-700")}>{ overall.expenseWithFormat }</p>
            </div>

            <div className={cn("flex-grow p-4 rounded-md shadow-md bg-white")}>
              <h4 className={cn("text-pretty text-neutral-500 font-bold")}>Net Assets</h4>
              <p className={cn("text-2xl font-semibold text-neutral-700")}>{ overall.balanceWithFormat }</p>
            </div>
          </div>
        </div>

        { /* control */}
        <div className={cn("flex justify-between")}>
          <Button variant={"outline"} onClick={handleOpenAccountForm}>
            <Plus /> Create Account
          </Button>

          <div className={cn("flex justify-between gap-2")}>
            <BalancePopover side="left" trigger={<Button variant={"ghost"}><Funnel /> 2 Filters</Button>}>
              <BalanceSearchFormControl label="Name" labelFor="name">
                <Input type="text" id="name" placeholder="Account name" />
              </BalanceSearchFormControl>

              <BalanceSearchFormControl label="Amount" labelFor="amount">
                <Input type="number" id="amount" value="0" />
              </BalanceSearchFormControl>
            </BalancePopover>
          </div>
        </div>

        {
          loading ? <Loading /> :
          accounts.length == 0 ? <DataNotFound data="account" /> :
          (
            <DataBoxContainer>
              {
                accounts.map(acc => (
                  <DataBox key={acc.id}
                    dataName={acc.name}
                    dataValue={acc.amountWithFormat}
                    dataIcon={acc.icon}
                    link={`/balance/app/user/account/${acc.id}`}
                  >
                    <DropdownMenuItem onClick={() => handleEdit(acc)}>Edit</DropdownMenuItem>
                    <DropdownMenuItem onClick={() => handleConfirm(acc.id)}>Delete</DropdownMenuItem>
                  </DataBox>
                ))
              }
            </DataBoxContainer>
          )
        }

      </MainPageLayout>

      <AccountForm
        form={form}
        open={openAccountForm}
        onClose={() => setOpenAccountForm(false)}
        onSubmit={handleSubmitAccountForm}
      />

      <BalanceAlertDialog
        open={openAlert}
        onClose={() => setOpenAlert(false)}
        onAction={handleDelete}
        actionText="OK"
        title="Delete Confirm"
      >
        Are you sure to delete?
      </BalanceAlertDialog>
    </>
  )
}

export default Account;
