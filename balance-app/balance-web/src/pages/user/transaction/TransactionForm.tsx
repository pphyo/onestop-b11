import React, { useState } from 'react'
import { UseFormReturn } from 'react-hook-form';
import { TransactionFormData } from './Transaction';
import { Form, FormField } from '@/components/ui/form';
import BalanceDialog from '@/components/widget/BalanceDialog';
import { cn, delay } from '@/lib/utils';
import { BalanceFormControl } from '@/components/widget/BalanceFormControl';
import { RadioGroup, RadioGroupItem } from '@/components/ui/radio-group';
import { Label } from '@/components/ui/label';

type TransactionFormProps = {
    form: UseFormReturn<TransactionFormData>;
    open: boolean,
    onClose: () => void;
    onSubmit: (data: TransactionFormData) => Promise<void>
};

const TransactionForm: React.FC<TransactionFormProps> = ({form, open, onClose, onSubmit}) => {

  const [submitting, setSubmitting] = useState(false);

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
                value={form.getValues("type")}
                onValueChange={(value) => {
                  field.onChange(value);
                  console.log(form.getValues("type"));
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

        </BalanceDialog>
      </Form>
    </>
  )
}

export default TransactionForm;