import React, { useState } from 'react'
import { UseFormReturn } from 'react-hook-form';
import { TransactionFormData } from './Transaction';
import { Form, FormField } from '@/components/ui/form';
import BalanceDialog from '@/components/widget/BalanceDialog';
import { cn, delay } from '@/lib/utils';
import { BalanceFormControl } from '@/components/widget/BalanceFormControl';
import { RadioGroup, RadioGroupItem } from '@/components/ui/radio-group';
import { Label } from '@/components/ui/label';
import { useCategory } from '@/hooks/useCategory';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import useAccount from '@/hooks/useAccount';
import { Input } from '@/components/ui/input';
import { Textarea } from '@/components/ui/textarea';

type TransactionFormProps = {
    form: UseFormReturn<TransactionFormData>;
    open: boolean,
    onClose: () => void;
    onSubmit: (data: TransactionFormData) => Promise<void>
};

const TransactionForm: React.FC<TransactionFormProps> = ({form, open, onClose, onSubmit}) => {

  const [submitting, setSubmitting] = useState(false);

  const {categories, refetch} = useCategory({name: "", income: undefined});

  const {accounts} = useAccount({amount: 0, name: ""});

  const handleSubmit = async (data: TransactionFormData) => {
    setSubmitting(true);
    try {
      await delay(500);
      await onSubmit(data);
      onClose();
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <>
      <Form {...form}>
        <BalanceDialog
          open={open}
          onClose={onClose}
          title={form.getValues("id") == 0 ? "New Transaction" : "Transaction Edit"}
          onAction={form.handleSubmit(handleSubmit)}
          actionText={submitting ? "Saving..." : "Save"}
        >

          <FormField control={form.control} name="type"
            render={({field}) => (
              <RadioGroup className={cn("flex justify-center items-center gap-4")}
                value={field.value}
                onValueChange={(value) => {
                  field.onChange(value);
                  if(field.value !== "Transfer")
                    refetch({name: "", income: value === "Income"});

                  form.reset({
                    id: form.getValues("id"),
                    amount: form.getValues("amount"),
                    type: form.getValues("type"),
                    note: form.getValues("note"),
                    account: 0,
                    category: 0,
                    accountFrom: 0,
                    accountTo: 0
                  });
                }}
              >
                <div className={cn("flex gap-1 items-center")}>
                  <RadioGroupItem id="income" value="Income" />
                  <Label htmlFor="income">Income</Label>
                </div>

                <div className={cn("flex gap-1 items-center")}>
                  <RadioGroupItem id="expense" value="Expense" />
                  <Label htmlFor="expense">Expense</Label>
                </div>

                <div className={cn("flex gap-1 items-center")}>
                  <RadioGroupItem id="transfer" value="Transfer" />
                  <Label htmlFor="transfer">Transfer</Label>
                </div>
              </RadioGroup>

            )}
          />

          <div className={cn("flex gap-2")}>
            {
              form.getValues("type") === "Transfer" ?
              (
                <>
                  <FormField control={form.control} name="accountFrom"
                    render={({field}) => (
                      <BalanceFormControl label="From" labelFor="from">
                        <Select
                          onValueChange={(value) => field.onChange(Number(value))}
                          value={field.value ? field.value.toString() : undefined}
                        >
                          <SelectTrigger className={cn("w-[182px]")} id="from">
                            <SelectValue placeholder="Select from account" />
                          </SelectTrigger>
                          <SelectContent>
                            {
                              accounts.map(acc => (
                                <SelectItem key={acc.id} value={acc.id.toString()}>{ acc.name }</SelectItem>
                              ))
                            }
                          </SelectContent>
                        </Select>
                      </BalanceFormControl>
                    )}
                  />

                  <FormField control={form.control} name="accountTo"
                    render={({field}) => (
                      <BalanceFormControl label="To" labelFor="to">
                        <Select
                          onValueChange={(value) => field.onChange(Number(value))}
                          value={field.value ? field.value.toString() : undefined}
                        >
                          <SelectTrigger className={cn("w-[182px]")} id="to">
                            <SelectValue placeholder="Select to account" />
                          </SelectTrigger>
                          <SelectContent>
                            {
                              accounts.map(acc => (
                                <SelectItem key={acc.id} value={acc.id.toString()}>{ acc.name }</SelectItem>
                              ))
                            }
                          </SelectContent>
                        </Select>
                      </BalanceFormControl>
                    )}
                  />
                </>
              ) :
              (
                <>
                  <FormField control={form.control} name="account"
                    render={({field}) => (
                      <BalanceFormControl label="Account" labelFor="account">
                        <Select
                          onValueChange={(value) => field.onChange(Number(value))}
                          value={field.value ? field.value.toString() : undefined}
                        >
                          <SelectTrigger className={cn("w-[182px]")} id="account">
                            <SelectValue placeholder="Select account" />
                          </SelectTrigger>
                          <SelectContent>
                            {
                              accounts.map(acc => (
                                <SelectItem key={acc.id} value={acc.id.toString()}>{ acc.name }</SelectItem>
                              ))
                            }
                          </SelectContent>
                        </Select>
                      </BalanceFormControl>
                    )}
                  />

                  <FormField control={form.control} name="category"
                    render={({field}) => (
                      <BalanceFormControl label="Category" labelFor="category">
                        <Select
                          onValueChange={(value) => field.onChange(Number(value))}
                          value={field.value ? field.value.toString() : undefined}
                        >
                          <SelectTrigger className={cn("w-[182px]")} id="category">
                            <SelectValue placeholder="Select category" />
                          </SelectTrigger>
                          <SelectContent>
                            {
                              categories.map(cat => (
                                <SelectItem key={cat.id} value={cat.id.toString()}>{ cat.name }</SelectItem>
                              ))
                            }
                          </SelectContent>
                        </Select>
                      </BalanceFormControl>
                    )}
                  />
                </>
              )
            }
          </div>

          <FormField control={form.control} name="amount"
            render={({field}) => (
              <BalanceFormControl label="Amount" labelFor="amount">
                <Input {...field} type="number" id="amount" value={field.value.toString() ?? 0} />
              </BalanceFormControl>
            )}
          />

          <FormField control={form.control} name="note"
            render={({field}) => (
              <BalanceFormControl label="Note" labelFor="note">
                <Textarea {...field} id="note" placeholder="Add notes" />
              </BalanceFormControl>
            )}
          />

        </BalanceDialog>
      </Form>
    </>
  )
}

export default TransactionForm;