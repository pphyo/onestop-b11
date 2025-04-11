import { Link } from "react-router"

export const Page = () => {
  return (
    <>
        <div>Page component work!</div>
        <Link to={"/home"}>To Home</Link>
    </>
  )
}

export default Page;
