import { delay } from "@/lib/utils";
import { CategoryOutputs } from "@/model/dto/balance.dto";
import { CategorySearchParam } from "@/model/dto/balance.search-param";
import { getCategoryService } from "@/model/service/category.service";
import { useCallback, useEffect, useState } from "react";

export const useCategory = ({name, income}: CategorySearchParam) => {
    const categoryService = getCategoryService();

    const [loading, setLoading] = useState<boolean>(false)
    const [categories, setCategories] = useState<CategoryOutputs>([]);

    const fetchCategories = useCallback(async (params: CategorySearchParam) => {
        setLoading(true);
        try {
            await delay(1000);
            const res = await categoryService.search(params);
            const categoriesList = res && res.payload;

            if (categoriesList.length)
                setCategories(categoriesList);

        } finally {
            setLoading(false);
        }
    }, [categoryService]);

    useEffect(() => {
        const fetchData = () => fetchCategories({name, income});
        fetchData();
    }, [fetchCategories, name, income]);

    return { categoryService, loading, categories, refetch: fetchCategories }
}