import { useParams } from "react-router";

const AccountDetail = () => {
    const params = useParams();
    return (
        <div>{ params.id }</div>
    )
}

export default AccountDetail;