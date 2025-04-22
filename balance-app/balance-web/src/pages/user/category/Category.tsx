import { Button } from "@/components/ui/button";
import { DropdownMenuItem } from "@/components/ui/dropdown-menu";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import BalanceDropdownMenu from "@/components/widget/BalanceDropdownMenu";
import BalancePopover from "@/components/widget/BalancePopover";
import BalanceSearchFormControl from "@/components/widget/BalanceSearchFormControl";
import CategoryControl from "@/components/widget/category/CategoryControl";
import CategoryForm from "@/components/widget/category/CategoryForm";
import MainPageTitle from "@/components/widget/MainPageTitle";
import MainPageLayout from "@/layouts/MainPageLayout";
import { cn } from "@/lib/utils";
import { getCategoryService } from "@/model/service/category.service";
import { zodResolver } from "@hookform/resolvers/zod";
import { ArrowDownAZ, ArrowDownZA, CalendarArrowDown, CalendarArrowUp, Funnel, ListFilter, Plus, Tags } from "lucide-react";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";

export const iconSchema = z.object({
    id: z.number(),
    name: z.string().min(1, {message: "Icon name required."}),
    path: z.string().min(1, {message: "Icon path required."})
});

const categoryFormSchema = z.object({
    id: z.number(),
    name: z.string().min(1, {message: "Category name required."}),
    income: z.coerce.boolean(),
    icon: iconSchema
});

export type CategoryFormData = z.infer<typeof categoryFormSchema>;

const Category = () => {

    const categoryService = getCategoryService();

    const form = useForm<CategoryFormData>({
        resolver: zodResolver(categoryFormSchema),
        defaultValues: {
            id: 0,
            name: "",
            income: true,
            icon: {id: 0, name: "", path: ""}
        }
    });

    const [openCategoryForm, setOpenCategoryForm] = useState<boolean>(false);

    const handleOpenCategoryForm = () => {
        setOpenCategoryForm(true);
    };

    const handleCloseCategoryForm = () => {
        resetForm();
        setOpenCategoryForm(false);
    };

    const handleSubmitCategoryForm = async (data: CategoryFormData) => {
        const result = await categoryService.save({
            id: data.id,
            name: data.name,
            income: data.income,
            iconId: data.icon.id
        });
        if(result) {
            console.log(result);
        }
        setOpenCategoryForm(false);
    };

    const resetForm = () => {
        form.reset();
        form.setValue("id", 0);
        form.setValue("name", "");
        form.setValue("income", true);
        form.setValue("icon", {id: 0, name: "", path: ""});
    };

    return (
        <>
            <MainPageLayout>
                <MainPageTitle icon={Tags} title="Your Categories" />
                <CategoryControl>
                    <Button variant={"outline"} onClick={handleOpenCategoryForm}>
                        <Plus /> Add New Category
                    </Button>
                    <div className={cn("flex gap-2 items-center")}>
                        <BalancePopover trigger={<Button variant={"ghost"}>
                                                    <Funnel /> 2 Filters
                                                </Button>}
                        >
                            <BalanceSearchFormControl label="Name" labelFor="name">
                                <Input type="text" id="name" placeholder="Category name" />
                            </BalanceSearchFormControl>
                            <BalanceSearchFormControl label="Type" labelFor="type">
                                <Select>
                                    <SelectTrigger className={cn("w-full")}>
                                        <SelectValue placeholder="Category type" />
                                    </SelectTrigger>
                                    <SelectContent>
                                        <SelectItem value="Both">Both</SelectItem>
                                        <SelectItem value="Income">Inome</SelectItem>
                                        <SelectItem value="Expense">Expense</SelectItem>
                                    </SelectContent>
                                </Select>
                            </BalanceSearchFormControl>
                        </BalancePopover>

                        <BalanceDropdownMenu trigger={
                                            <Button variant={"ghost"}>
                                                <ListFilter /> Sort
                                            </Button>
                        }>
                            <DropdownMenuItem>
                                <ArrowDownAZ /> Name Asc
                            </DropdownMenuItem>
                            <DropdownMenuItem>
                                <ArrowDownZA /> Name Desc
                            </DropdownMenuItem>
                            <DropdownMenuItem>
                                <CalendarArrowDown /> Date Asc
                            </DropdownMenuItem>
                            <DropdownMenuItem>
                                <CalendarArrowUp /> Date Desc
                            </DropdownMenuItem>
                        </BalanceDropdownMenu>
                    </div>
                </CategoryControl>
            </MainPageLayout>

            <CategoryForm
                open={openCategoryForm}
                onClose={handleCloseCategoryForm}
                form={form}
                onSubmit={handleSubmitCategoryForm}
                initialType={form.getValues("income")}
            />
        </>
    )
  }

  export default Category;