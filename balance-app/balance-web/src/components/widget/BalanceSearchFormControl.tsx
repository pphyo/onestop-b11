import { cn } from '@/lib/utils';
import React from 'react'
import { Label } from '@/components/ui/label';

type SearchFormControlProps = {
    label: string;
    labelFor: string;
    children: React.ReactNode
}

const BalanceSearchFormControl: React.FC<SearchFormControlProps> = ({label, labelFor, children}) => {
  return (
    <div className={cn("flex flex-col gap-2")}>
        <Label htmlFor={labelFor}>{label}</Label>
        {children}
    </div>
  )
}

export default BalanceSearchFormControl;