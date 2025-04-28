import { Button } from "@/components/ui/button";
import { DropdownMenuItem } from "@/components/ui/dropdown-menu";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import BalanceAlertDialog from "@/components/widget/BalanceAlertDialog";
import BalanceDropdownMenu from "@/components/widget/BalanceDropdownMenu";
import BalancePopover from "@/components/widget/BalancePopover";
import BalanceSearchFormControl from "@/components/widget/BalanceSearchFormControl";
import CategoryForm from "@/pages/user/category/CategoryForm";
import DataBox from "@/components/widget/DataBox";
import DataBoxContainer from "@/components/widget/DataBoxContainer";
import DataNotFound from "@/components/widget/DataNotFound";
import Loading from "@/components/widget/Loading";
import MainPageTitle from "@/components/widget/MainPageTitle";
import { useCategory } from "@/hooks/useCategory";
import MainPageLayout from "@/layouts/MainPageLayout";
import { cn } from "@/lib/utils";
import { CategoryIconFilter, CategorySearchParam } from "@/model/dto/balance.search-param";
import { getCategoryService } from "@/model/service/category.service";
import { zodResolver } from "@hookform/resolvers/zod";
import { ArrowDownAZ, ArrowDownZA, CalendarArrowDown, CalendarArrowUp, Funnel, ListFilter, Plus, Tags } from "lucide-react";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { useLocation } from "react-router";
import { z } from "zod";
import { CategoryDto, IconDto } from "@/model/dto/balance.dto";

export const iconFormSchema = z.object({
    id: z.number(),
    name: z.string(),
    path: z.string()
});

const categoryFormSchema = z.object({
    id: z.number(),
    name: z.string().min(1, {message: "Category name required."}),
    income: z.coerce.boolean(),
    icon: iconFormSchema
});

export type CategoryFormData = z.infer<typeof categoryFormSchema>;

const Category = () => {

    const categoryService = getCategoryService();

    const [openCategoryForm, setOpenCategoryForm] = useState(false);

    const [openAlert, setOpenAlert] = useState(false);

    const [openPopover, setOpenPopover] = useState(false);

    const [openDropdown, setOpenDropdown] = useState(false);

    const [idForDelete, setIdForDelete] = useState(0);

    const [categoryParams, setCategoryParams] = useState<CategorySearchParam>({name: "", income: undefined});

    const { loading, categories, refetch } = useCategory(categoryParams);

    const form = useForm<CategoryFormData>({
        resolver: zodResolver(categoryFormSchema),
        defaultValues: {
            id: 0,
            name: "",
            income: true,
            icon: {id: 0, name: "", path: ""}
        }
    });

    const location = useLocation();

    const handleOpenCategoryForm = () => {
        resetForm();
        setOpenPopover(false);
        setOpenDropdown(false);
        setOpenAlert(false);
        setOpenCategoryForm(true);
    };

    const handleNameParamChange = (name: string) => {
        const prev = categoryParams;
        setCategoryParams({
            name,
            income: prev.income
        });
    };

    const handleIncomeParamChange = (income: string) => {
        const prev = categoryParams;
        setCategoryParams({
            income: income.toLowerCase() === "all" ? undefined : Boolean(income),
            name: prev.name
        });
    };

    const handleSubmitCategoryForm = async (data: CategoryFormData) => {
        const response = await categoryService.save({
            id: data.id,
            name: data.name,
            income: data.income,
            icon: data.icon.id
        });
        if(response) {
            refetch({name: "", income: undefined})
        }
        setOpenCategoryForm(false);
        resetForm();
    };

    const handleConfirm = (id: number) => {
        setOpenAlert(true);
        setIdForDelete(id);
    };

    const handleEdit = (data: CategoryDto<IconDto>) => {
        resetForm();
        form.setValue("id", data.id);
        form.setValue("name", data.name);
        form.setValue("income", data.income as boolean);
        form.setValue("icon", data.icon);
        setOpenCategoryForm(true);
    };

    const handleDelete = async () => {
        const response = await categoryService.delete(idForDelete);
        if(response) {
            setCategoryParams({name: "", income: undefined});
        }
        setOpenAlert(false);
        setIdForDelete(0);
    };

    const resetForm = () => {
        form.setValue("id", 0);
        form.setValue("name", "");
        form.setValue("income", true);
        form.setValue("icon", {id: 0, name: "", path: ""});
    };

    return (
        <>
            <MainPageLayout>
                <MainPageTitle icon={Tags} title="Categories" />
                <div className={cn("flex justify-between")}>
                    <Button variant={"outline"} onClick={handleOpenCategoryForm}>
                        <Plus /> Add New Category
                    </Button>
                    <div className={cn("flex gap-2 items-center")}>
                        <BalancePopover trigger={<Button variant={"ghost"}><Funnel /> 2 Filters</Button>}
                            open={openPopover} onOpenChange={setOpenPopover} side="left"
                        >
                            <BalanceSearchFormControl label="Name" labelFor="name">
                                <Input type="text" id="name" placeholder="Category name" onChange={e => handleNameParamChange(e.target.value)} />
                            </BalanceSearchFormControl>
                            <BalanceSearchFormControl label="Type" labelFor="type">
                                <Select onValueChange={handleIncomeParamChange}>
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

                        <BalanceDropdownMenu trigger={<Button variant={"ghost"}><ListFilter /> Sort</Button>}
                            open={openDropdown} onOpenChange={setOpenDropdown} side="left"
                        >
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
                </div>

                {
                    loading ? <Loading /> :
                    categories.length == 0 ? <DataNotFound data="category" /> :
                    <DataBoxContainer>
                        {
                            categories.map(cat => (
                                <DataBox key={cat.id}
                                    link={`${location.pathname}/${cat.id}`}
                                    dataName={cat.name}
                                    dataValue={cat.income ? "Income" : "Expense"}
                                    dataIcon={cat.icon}>
                                        <DropdownMenuItem onClick={() => handleEdit(cat)}>Edit</DropdownMenuItem>
                                        <DropdownMenuItem onClick={() => handleConfirm(cat.id)}>Delete</DropdownMenuItem>
                                </DataBox>
                            ))
                        }
                    </DataBoxContainer>
                }

            </MainPageLayout>

            <CategoryForm
                open={openCategoryForm}
                onClose={() => setOpenCategoryForm(false)}
                form={form}
                onSubmit={handleSubmitCategoryForm}
                initialFilter={form.getValues("income") == true ? "Income" : "Expense" as CategoryIconFilter}
            />

            <BalanceAlertDialog open={openAlert} onClose={() => setOpenAlert(false)} onAction={handleDelete} actionText="Confirm" title="Delete Confirm">
                Are you sure to delete?
            </BalanceAlertDialog>
        </>
    )
  }

  export default Category;