import { Button } from "@/components/ui/button";
import { DropdownMenuItem } from "@/components/ui/dropdown-menu";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import BalanceDropdownMenu from "@/components/widget/BalanceDropdownMenu";
import BalancePopover from "@/components/widget/BalancePopover";
import BalanceSearchFormControl from "@/components/widget/BalanceSearchFormControl";
import CategoryControl from "@/components/widget/category/CategoryControl";
import MainPageTitle from "@/components/widget/MainPageTitle";
import MainPageLayout from "@/layouts/MainPageLayout";
import { cn } from "@/lib/utils";
import { ArrowDownAZ, ArrowDownZA, CalendarArrowDown, CalendarArrowUp, Funnel, ListFilter, Plus, Tags } from "lucide-react";

const Category = () => {
    return (
      <MainPageLayout>
        <MainPageTitle icon={Tags} title="Your Categories" />
        <CategoryControl>
            <div>
                <Button variant={"outline"}>
                    <Plus /> Add New Category
                </Button>
            </div>
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
    )
  }

  export default Category;