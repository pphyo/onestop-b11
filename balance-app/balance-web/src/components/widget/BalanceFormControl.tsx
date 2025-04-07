import React from 'react'
import { FormControl, FormDescription, FormItem, FormLabel, FormMessage } from '@/components/ui/form';

type BalanceFormControlProps = {
    children: React.ReactNode;
    label: string;
    labelFor: string;
    description?: string;
}

export const BalanceFormControl: React.FC<BalanceFormControlProps> = ({children, label, labelFor, description}) => {
  return (
    <FormItem>
      <FormLabel htmlFor={labelFor}>{label}</FormLabel>
      <FormControl>
        {children}
      </FormControl>
      {description && <FormDescription>{description}</FormDescription>}
      <FormMessage />
    </FormItem>
  )
}
