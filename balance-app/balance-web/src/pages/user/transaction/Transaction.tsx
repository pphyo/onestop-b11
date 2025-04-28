import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import BalancePopover from "@/components/widget/BalancePopover";
import BalanceSearchFormControl from "@/components/widget/BalanceSearchFormControl";
import { BalanceTooltip } from "@/components/widget/BalanceTooltip";
import MainPageTitle from "@/components/widget/MainPageTitle";
import MainPageLayout from "@/layouts/MainPageLayout";
import { cn } from "@/lib/utils";
import { ArrowDown01, ArrowDown10, CalendarArrowDown, CalendarArrowUp, ChevronLeft, ChevronRight, Funnel, ListFilter, NotepadText, Plus } from "lucide-react";
import { ChangeEventHandler, useMemo, useState } from "react";
import { addMonths, format, subMonths } from "date-fns";
import BalanceDropdownMenu from "@/components/widget/BalanceDropdownMenu";
import { DropdownMenuItem } from "@/components/ui/dropdown-menu";
import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import TransactionForm from "./TransactionForm";
import { AccountDto, CategoryDto, IconDto, TransactionDto } from "@/model/dto/balance.dto";
import { getTransactionService } from "@/model/service/transaction.service";
import useTransaction from "@/hooks/useTransaction";
import Loading from "@/components/widget/Loading";
import DataNotFound from "@/components/widget/DataNotFound";
import TransactionList from "./TransactionList";
import BalanceAlertDialog from "@/components/widget/BalanceAlertDialog";

const transactionFormBaseSchema = z.object({
  id: z.number(),
  amount: z.coerce.number().gt(0, {message: "Amount must be positive"}),
  note: z.string()
});

const transactionForIncomeExpenseFormSchema = transactionFormBaseSchema.extend({
  type: z.enum(["Income", "Expense"]),
  account: z.number().min(1, {message: "Account required."}),
  category: z.number().min(1, {message: "Category required."})
});

const transactionForTransferFormSchema = transactionFormBaseSchema.extend({
  type: z.literal("Transfer"),
  accountFrom: z.number().min(1, {message: "From account required."}),
  accountTo: z.number().min(1, {message: "To account required."})
});

const transactionFormSchema = z.discriminatedUnion("type", [
  transactionForIncomeExpenseFormSchema,
  transactionForTransferFormSchema
]).refine((data) => {
  if(data.type === "Transfer") {
    return data.accountFrom !== data.accountTo
  }
  return true;
}, {
  message: "From account and To account must be different.",
  path: ["accountTo"]
});

export type TransactionFormData = z.infer<typeof transactionFormSchema>;

const Transaction = () => {

  const transactionService = getTransactionService();

  const form = useForm<TransactionFormData>({
    resolver: zodResolver(transactionFormSchema),
    defaultValues: {
      id: 0,
      amount: 0,
      type: "Expense",
      note: "",
      account: 0,
      category: 0,
      accountFrom: 0,
      accountTo: 0
    } as TransactionFormData
  })

  const [openAlert, setOpenAlert] = useState(false);

  const [openTransactionForm, setOpenTransactionForm] = useState(false);

  const [monthParam, setMonthParam] = useState(new Date());

  const [params, setParams] = useState({amount: 0, keyword: ""});

  const transactionParam = useMemo(() => ({
    month: format(monthParam, "yyyy-MM"),
    amount: params.amount,
    keyword: params.keyword
  }), [monthParam, params]);

  const {loading, transactions, refetch} = useTransaction(transactionParam);

  const [idForDelete, setIdForDelete] = useState(0);

  const handlePrevMonth = () => {
    setMonthParam(subMonths(monthParam, 1));
  };

  const handleNextMonth = () => {
    setMonthParam(addMonths(monthParam, 1));
  };

  const handleAmountChange: ChangeEventHandler<HTMLInputElement> = (event) => {
    const value = Number(event.target.value || 0)
    setParams(prev => ({...prev, amount: value}))
  };

  const handleKeywordChange: ChangeEventHandler<HTMLInputElement> = (event) => {
    setParams(prev => ({...prev, keyword: event.target.value}))
  };

  const handleOpenTransactionForm = () => {
    resetForm();
    setOpenTransactionForm(true);
  };

  const handleSubmitTransactionForm = async (data: TransactionFormData) => {
    const resp = await transactionService.save(data);
    if(resp) {
      resetParams();
      refetch(transactionParam);
    }
    setOpenTransactionForm(false);
    resetForm();
  };

  const handleEdit = (dto: TransactionDto<AccountDto<IconDto>, AccountDto<IconDto> | CategoryDto<IconDto>>) => {
    resetForm();
    form.setValue("id", dto.id);
    form.setValue("amount", dto.amount);
    form.setValue("note", dto.note);
    form.setValue("type", dto.type);

    if(dto.type === "Transfer") {
      if(dto.accountFrom && dto.accountTo) {
        form.setValue("accountFrom", dto.accountFrom.id);
        form.setValue("accountTo", dto.accountTo.id);
      }
    } else {
      if(dto.account && dto.category) {
        form.setValue("account", dto.account.id);
        form.setValue("category", dto.category.id)
      }
    }

    setOpenTransactionForm(true);
  };

  const handleConfirm = (id: number) => {
    setIdForDelete(id);
    setOpenAlert(true);
  };

  const handleDelete = async () => {
    if(idForDelete) {
      const resp = await transactionService.delete(idForDelete);

      if(resp) {
        setOpenAlert(false);
        setIdForDelete(0);
      }
    }
  };

  const resetParams = () => {
    setMonthParam(new Date);
    setParams({amount: 0, keyword: ""});
  };

  const resetForm = () => {
    form.reset({
      id: 0,
      amount: 0,
      type: "Expense",
      note: "",
      account: 0,
      category: 0,
      accountFrom: 0,
      accountTo: 0
    } as TransactionFormData);
  };

  return (
    <>
      <MainPageLayout>
        <MainPageTitle icon={NotepadText} title="Transactions" />

        <div className={cn("flex justify-between")}>
          <Button onClick={handleOpenTransactionForm} variant={"outline"} className={cn("w-[200px]")}>
            <Plus /> Create Transaction
          </Button>

          <div className={cn("flex justify-between items-center w-[200px]")}>
            <BalanceTooltip content="Prev Month">
              <Button size={"icon"} variant={"outline"}
                className={cn("rounded-full")}
                onClick={handlePrevMonth}
              >
                <ChevronLeft />
              </Button>
            </BalanceTooltip>

            <span className={cn("text-pretty font-semibold text-neutral-700")}>
              { format(monthParam, "MMM yyyy") }
            </span>

            <BalanceTooltip content="Next Month">
              <Button size={"icon"} variant={"outline"}
                className={cn("rounded-full")}
                onClick={handleNextMonth}
              >
                <ChevronRight />
              </Button>
            </BalanceTooltip>
          </div>

          <div className={cn("flex gap-2 w-[200px]")}>
            <BalancePopover side="left"
              trigger={<Button variant={"ghost"}><Funnel /> 2 Filters</Button>}
            >
              <BalanceSearchFormControl label="Amount" labelFor="amount">
                <Input type="number" id="amount" value={params.amount} onChange={handleAmountChange} />
              </BalanceSearchFormControl>

              <BalanceSearchFormControl label="Keyword" labelFor="keyword">
                <Input type="text" id="keyword" value={params.keyword} onChange={handleKeywordChange} placeholder="Keyword to search" />
              </BalanceSearchFormControl>
            </BalancePopover>

            <BalanceDropdownMenu side="left"
              trigger={<Button variant={"ghost"}><ListFilter /> Sort</Button>}
            >
              <DropdownMenuItem>
                <ArrowDown01 /> Amount Asc
              </DropdownMenuItem>
              <DropdownMenuItem>
                <ArrowDown10 /> Amount Desc
              </DropdownMenuItem>
              <DropdownMenuItem>
                <CalendarArrowDown /> Date Asc
              </DropdownMenuItem>
              <DropdownMenuItem>
                <CalendarArrowUp /> Date Desc
              </DropdownMenuItem>
            </BalanceDropdownMenu>
          </div>
        </div>

        {
          loading ? <Loading /> :
          !transactions ? <DataNotFound data="transaction" /> :
          <TransactionList
            data={transactions}
            onDelete={handleConfirm}
            onEdit={handleEdit}
          />
        }
      </MainPageLayout>

      <TransactionForm
        form={form}
        open={openTransactionForm}
        onClose={() => setOpenTransactionForm(false)}
        onSubmit={handleSubmitTransactionForm}
      />

      <BalanceAlertDialog
        open={openAlert}
        onClose={() => setOpenAlert(false)}
        title="Delete Confirm"
        onAction={handleDelete}
        actionText="Delete"
      >
        Are you sure to delete?
      </BalanceAlertDialog>
    </>
  )
}

export default Transaction;