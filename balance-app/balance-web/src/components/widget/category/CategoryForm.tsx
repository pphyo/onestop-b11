import { Form, FormField } from '@/components/ui/form';
import { CategoryFormData } from '@/pages/user/category/Category';
import React, { useEffect, useState } from 'react'
import { UseFormReturn } from 'react-hook-form';
import BalanceDialog from '@/components/widget/BalanceDialog';
import { BalanceFormControl } from '@/components/widget/BalanceFormControl';
import { Input } from '@/components/ui/input';
import { RadioGroup, RadioGroupItem } from '@/components/ui/radio-group';
import { cn, delay } from '@/lib/utils';
import { Label } from '@/components/ui/label';
import { getIconService } from '@/model/service/icon.service';
import { IconDtos } from '@/model/dto/balance.dto';
import { CategoryIconFilter } from '@/model/dto/balance.search-param';

type CategoryFormProps = {
    form: UseFormReturn<CategoryFormData>;
    open: boolean;
    onClose: () => void;
    onSubmit: (data: CategoryFormData) => Promise<void>;
    initialFilter: CategoryIconFilter;
}

const CategoryForm: React.FC<CategoryFormProps> = ({form, open, onClose, onSubmit, initialFilter}) => {

    const iconService = getIconService();
    const [icons, setIcons] = useState<IconDtos>([]);
    const [submitting, setSubmitting] = useState(false);

    useEffect(() => {
        if(!open)
            return;

        const newParam = {name: "", filter: initialFilter, account: false};

        const fetchIcons = async () => {
            const result = await iconService.search(newParam); // Use param directly
            setIcons(result.payload);
        };

        fetchIcons();
    }, [open, initialFilter, iconService]);

    const handleSubmit = async (data: CategoryFormData) => {
        setSubmitting(true);
        try {
            await delay(500);
            await onSubmit(data); // Wait for submission to complete
            onClose(); // Close the dialog after submission
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <Form {...form}>
            <BalanceDialog
                open={open}
                onClose={onClose}
                title={form.getValues("id") == 0 ? "Create Category" : "Category Edit"}
                onAction={form.handleSubmit(handleSubmit)}
                actionText={submitting ? "Saving..." : "Save"}
            >
                <FormField control={form.control} name="name"
                    render={({field}) => (
                        <BalanceFormControl label="Name" labelFor="name">
                            <Input {...field}
                                type="text"
                                value={field.value ?? ""}
                                id="name"
                                placeholder="Category name" />
                        </BalanceFormControl>
                    )}
                />

                <FormField control={form.control} name="income"
                    render={({field}) => (
                        <BalanceFormControl label="Category Type">
                            <RadioGroup className={cn("flex items-center gap-4 pt-1")}
                                value={field.value.toString()}
                                onValueChange={(value: string) => {
                                    field.onChange(value === "true");
                                    const newParam = {name: "", filter: (value === "true" ? "Income" : "Expense") as CategoryIconFilter, account: false};
                                    iconService.search(newParam).then(res => setIcons(res.payload));
                                }}
                            >
                                <div className={cn("flex gap-1")}>
                                    <RadioGroupItem value="true" id="income" />
                                    <Label htmlFor="income">Income</Label>
                                </div>

                                <div className={cn("flex gap-1")}>
                                    <RadioGroupItem value="false" id="expense" />
                                    <Label htmlFor="expense">Expense</Label>
                                </div>
                            </RadioGroup>
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
}

export default CategoryForm;