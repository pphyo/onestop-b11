import { Button } from "@/components/ui/button";
import { DropdownMenuItem } from "@/components/ui/dropdown-menu";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import BalanceAlertDialog from "@/components/widget/BalanceAlertDialog";
import BalanceDropdownMenu from "@/components/widget/BalanceDropdownMenu";
import BalancePopover from "@/components/widget/BalancePopover";
import BalanceSearchFormControl from "@/components/widget/BalanceSearchFormControl";
import CategoryControl from "@/components/widget/category/CategoryControl";
import CategoryForm from "@/components/widget/category/CategoryForm";
import DataBox from "@/components/widget/DataBox";
import DataBoxContainer from "@/components/widget/DataBoxContainer";
import DataNotFound from "@/components/widget/DataNotFound";
import Loading from "@/components/widget/Loading";
import MainPageTitle from "@/components/widget/MainPageTitle";
import useCategory from "@/hooks/useCategory";
import MainPageLayout from "@/layouts/MainPageLayout";
import { cn } from "@/lib/utils";
import { CategoryOutput } from "@/model/dto/balance.dto";
import { CategorySearchParam } from "@/model/dto/balance.search-param";
import { getCategoryService } from "@/model/service/category.service";
import { zodResolver } from "@hookform/resolvers/zod";
import { ArrowDownAZ, ArrowDownZA, CalendarArrowDown, CalendarArrowUp, Funnel, ListFilter, Plus, Tags } from "lucide-react";
import { ChangeEvent, useState } from "react";
import { useForm } from "react-hook-form";
import { useLocation } from "react-router";
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

    const [openAlert, setOpenAlert] = useState<boolean>(false);

    const [openCategoryForm, setOpenCategoryForm] = useState<boolean>(false);

    const [categoryParams, setCategoryParams] = useState<CategorySearchParam>({name: "", income: undefined});

    const {loading, categories, refetch} = useCategory(categoryParams);

    const [idForDelete, setIdForDelete]  = useState<number>(0);

    const location = useLocation();

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
            refetch({name: "", income: undefined});
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

    const handleNameParamChange = (event: ChangeEvent<HTMLInputElement>) => {
        setCategoryParams((prev) => ({...prev, name: event.target.value}));
    };

    const handleIncomeParamChange = (value: string) => {
        setCategoryParams((prev) => ({...prev, income: value == "Both" ? undefined : value == "Income" ? true : false}));
    };

    const handleEditCategory = (category: CategoryOutput) => {
        resetForm();
        form.setValue("id", category.id);
        form.setValue("name", category.name);
        form.setValue("income", category.income);
        form.setValue("icon", category.icon);
        setOpenCategoryForm(true);
    };

    const handleConfirm = (id: number) => {
        setIdForDelete(id);
        setOpenAlert(true);
    };

    const handleAlertAction = async () => {
        const result = await categoryService.delete(idForDelete);
        if(result) {
            setOpenAlert(false);
            refetch({name: "", income: undefined});
        }
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
                                <Input type="text" id="name" placeholder="Category name" onChange={handleNameParamChange} />
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

                        <BalanceDropdownMenu side="left" trigger={
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
                                        <DropdownMenuItem onClick={() => handleEditCategory(cat)}>Edit</DropdownMenuItem>
                                        <DropdownMenuItem onClick={() => handleConfirm(cat.id)}>Delete</DropdownMenuItem>
                                </DataBox>
                            ))
                        }
                    </DataBoxContainer>
                }

            </MainPageLayout>

            <CategoryForm
                open={openCategoryForm}
                onClose={handleCloseCategoryForm}
                form={form}
                onSubmit={handleSubmitCategoryForm}
                initialType={form.getValues("income")}
            />

            <BalanceAlertDialog open={openAlert} onClose={() => setOpenAlert(false)} onAction={handleAlertAction} actionText="Delete" title="Delete Confirm">
                Are you sure to delete?
            </BalanceAlertDialog>
        </>
    )
  }

  export default Category;