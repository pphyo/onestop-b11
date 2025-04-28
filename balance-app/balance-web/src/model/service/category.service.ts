import { BASE_API } from "@/lib/consts.type";
import { BalanceApiPayload, CategoryDto, CategoryDtos, IconDto } from "../dto/balance.dto";
import axiosInstance from "@/axios/axios-instance";
import { CategorySearchParam } from "../dto/balance.search-param";

const CATEGORIES_API = `${BASE_API}/user/categories`;

class CategoryService {
    private static instance: CategoryService;

    private constructor() {}

    public static getInstance(): CategoryService {
        if(!CategoryService.instance) {
            CategoryService.instance = new CategoryService();
        }
        return CategoryService.instance;
    }

    async save(data: CategoryDto): Promise<CategoryDto<IconDto>> {
        const {id, ...rest} = data;
        return id === 0 ?
                await axiosInstance.post(CATEGORIES_API, rest).then(res => res.data) :
                await axiosInstance.put(`${CATEGORIES_API}/${id}`, rest).then(res => res.data);
    }

    async search(params: CategorySearchParam): Promise<BalanceApiPayload<CategoryDtos>> {
        return await axiosInstance.get(CATEGORIES_API, {params}).then(res => res.data);
    }

    async searchById(id: number): Promise<BalanceApiPayload<CategoryDto<IconDto>>> {
        return await axiosInstance.get(`${CATEGORIES_API}/${id}`).then(res => res.data);
    }

    async delete(id: number): Promise<boolean> {
        return await axiosInstance.delete(`${CATEGORIES_API}/${id}`).then(res => res.data);
    }
}

export const getCategoryService = () => CategoryService.getInstance();