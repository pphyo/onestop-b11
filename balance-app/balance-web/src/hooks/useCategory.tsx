import { delay } from "@/lib/utils";
import { CategoryOutputs } from "@/model/dto/balance.dto";
import { CategorySearchParam } from "@/model/dto/balance.search-param";
import { getCategoryService } from "@/model/service/category.service";
import { useCallback, useEffect, useState } from "react";

const useCategory = ({name, income}: CategorySearchParam) => {
    const categoryService = getCategoryService();
    const [loading, setLoading] = useState<boolean>(false);
    const [categories, setCategories] = useState<CategoryOutputs>([]);

    const fetchCategories = useCallback(async (params: CategorySearchParam) => {
        setLoading(true);
        try {
            await delay(1000);
            const result = await categoryService.search(params);
            const categoryList = result && result.payload;
            if(categoryList.length) {
                setCategories(categoryList);
            }
        } finally {
            setLoading(false);
        }
    }, [categoryService]);

    useEffect(() => {
        const fetchData = () => fetchCategories({name, income});
        fetchData();
    }, [name, income, fetchCategories]);

    return {loading, categories, refetch: fetchCategories};
}

export default useCategory;