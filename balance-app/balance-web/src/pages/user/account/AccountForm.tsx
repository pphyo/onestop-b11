import React from 'react';
import { UseFormReturn } from 'react-hook-form';
import { AccountFormData } from './Account';
import { getIconService } from '@/model/service/icon.service';
import { useState } from 'react';
import { IconDtos } from '@/model/dto/balance.dto';
import { useEffect } from 'react';
import { Form, FormField } from '@/components/ui/form';
import BalanceDialog from '@/components/widget/BalanceDialog';
import { BalanceFormControl } from '@/components/widget/BalanceFormControl';
import { Input } from '@/components/ui/input';
import {delay, cn} from '@/lib/utils';
import {RadioGroup, RadioGroupItem} from '@/components/ui/radio-group';
import {Label} from '@/components/ui/label';

type AccountFormProps = {
    form: UseFormReturn<AccountFormData>;
    open: boolean;
    onClose: () => void;
    onSubmit: (data: AccountFormData) => Promise<void>;
}

const AccountForm: React.FC<AccountFormProps> = ({form, open, onClose, onSubmit}) => {
    const iconService = getIconService();
    const [icons, setIcons] = useState<IconDtos>([]);
    const [submitting, setSubmitting] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
            const resp = await iconService.search({name: "", account: true, filter: undefined});
            if(resp && resp.payload)
                setIcons(resp.payload);
        };

        if(open)
            fetchData();
        else
            setIcons([]);

    }, [iconService, open]);

    const handleSubmit = async (data: AccountFormData) => {
        setSubmitting(true);
        try {
            await delay(500);
            onSubmit(data);
            onClose();
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <Form {...form}>
            <BalanceDialog open={open}
                onClose={onClose}
                title={form.getValues("id") === 0 ? "Create Account" : "Account Edit"}
                onAction={form.handleSubmit(handleSubmit)}
                actionText={submitting ? "Saving..." : "Save"}
            >
                <FormField control={form.control} name="name"
                    render={({field}) => (
                        <BalanceFormControl label="Name" labelFor="name">
                            <Input {...field} value={field.value ?? ""} type="text" id="name" placeholder="Account name" />
                        </BalanceFormControl>
                    )}
                />

                <FormField control={form.control} name="amount"
                    render={({field}) => (
                        <BalanceFormControl label="Amount" labelFor="amount">
                            <Input {...field} value={field.value ?? 0} type="number" id="amount" />
                        </BalanceFormControl>
                    )}
                />

                <FormField control={form.control} name="icon"
                    render={({field}) => (
                        <BalanceFormControl label="Icon">
                            <div className={cn("max-h-28 overflow-y-auto")}>
                                <RadioGroup className={cn("grid grid-cols-4 gap-2 overflow-x-auto whitespace-nowrap")}
                                    onValueChange={(value: string) => {
                                        const result = icons.find(icon => icon.id == + value);
                                        field.onChange(result);
                                    }}
                                    value={field.value.id.toString() ?? ""}
                                >
                                    {
                                        icons.map(i => (
                                            <div key={i.id} className={cn("flex flex-col items-center p-2 rounded-md hover:cursor-pointer", field.value.id == i.id ? "bg-blue-100 border-blue-500" : "bg-white border-gray-300")}>
                                                <RadioGroupItem value={i.id.toString()} id={`icon-${i.id}`} className={cn("sr-only")} />
                                                <Label htmlFor={`icon-${i.id}`} className={cn("cursor-pointer")}>
                                                    <img src={i.path} alt={i.name} className={cn("w-8 h-8 object-contain")} />
                                                </Label>
                                            </div>
                                        ))
                                    }
                                </RadioGroup>
                            </div>
                        </BalanceFormControl>
                    )}
                />
            </BalanceDialog>
        </Form>
    );
};

export default AccountForm;