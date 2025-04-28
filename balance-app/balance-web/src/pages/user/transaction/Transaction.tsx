import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import BalancePopover from "@/components/widget/BalancePopover";
import BalanceSearchFormControl from "@/components/widget/BalanceSearchFormControl";
import { BalanceTooltip } from "@/components/widget/BalanceTooltip";
import MainPageTitle from "@/components/widget/MainPageTitle";
import MainPageLayout from "@/layouts/MainPageLayout";
import { cn } from "@/lib/utils";
import { ArrowDown01, ArrowDown10, CalendarArrowDown, CalendarArrowUp, ChevronLeft, ChevronRight, Funnel, ListFilter, NotepadText, Plus } from "lucide-react";
import { useMemo, useState } from "react";
import { addMonths, format, subMonths } from "date-fns";
import BalanceDropdownMenu from "@/components/widget/BalanceDropdownMenu";
import { DropdownMenuItem } from "@/components/ui/dropdown-menu";
import { z } from "zod";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { requestFormReset } from "react-dom";
import TransactionForm from "./TransactionForm";
import { TransactionDto } from "@/model/dto/balance.dto";

const transactionFormBaseSchema = z.object({
  id: z.number(),
  amount: z.coerce.number().gt(0, {message: "Amount must be positive"}),
  issuedAt: z.date().optional(),
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

  const form = useForm<TransactionFormData>({
    resolver: zodResolver(transactionFormSchema),
    defaultValues: {
      id: 0,
      amount: 0,
      type: "Expense",
      issuedAt: new Date,
      note: "",
      account: 0,
      category: 0,
      accountFrom: 0,
      accountTo: 0
    } as TransactionFormData
  })

  const [openTransactionForm, setOpenTransactionForm] = useState(false);

  const [monthParam, setMonthParam] = useState(new Date());

  const [params, setParams] = useState({amount: 0, keyword: ""});

  const transactionParam = useMemo(() => ({
    month: format(monthParam, "yyyy-MM"),
    amount: params.amount,
    keyword: params.keyword
  }), [monthParam, params]);

  const handlePrevMonth = () => {
    setMonthParam(subMonths(monthParam, 1));
  };

  const handleNextMonth = () => {
    setMonthParam(addMonths(monthParam, 1));
  };

  const handleOpenTransactionForm = () => {
    resetForm();
    setOpenTransactionForm(true);
  };

  const handleSubmitTransactionForm = (data: TransactionDto) => {
    console.log(data);
  };

  const resetForm = () => {
    form.reset({
      id: 0,
      amount: 0,
      type: "Expense",
      issuedAt: new Date,
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
                <Input type="number" id="amount" value="0" />
              </BalanceSearchFormControl>

              <BalanceSearchFormControl label="Keyword" labelFor="keyword">
                <Input type="text" id="keyword" value="" placeholder="Keyword to search" />
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
      </MainPageLayout>

      <TransactionForm
        form={form}
        open={openTransactionForm}
        onClose={() => setOpenTransactionForm(false)}
        onSubmit={handleSubmitTransactionForm}
      />
    </>
  )
}

export default Transaction;