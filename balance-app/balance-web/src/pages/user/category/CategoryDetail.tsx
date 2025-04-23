import { useParams } from "react-router";

const CategoryDetail = () => {
    const params = useParams();
    return (
        <div>{ params.id }</div>
    )
}

export default CategoryDetail;