import { useTheme } from "./useTheme";
import { useUser } from "./useUser";

export const ProfilePage = () => {

  const {user, login, logout} = useUser();
  const {theme, setTheme} = useTheme();

  return (
    <div>
        <h1>Profile Page</h1>
        {
            user.isLogin ?
            <>
                <p>အမည်: {user.name}</p>

                <button onClick={() => logout({name: "တင်ဝင်းကြိုင်", isLogin: false})}>ထွက်ရန်</button>
            </>
            :
            <>
                <p>ကျေးဇူးပြု၍ အကောင့်ဝင်ပါ။</p>
                <button onClick={() => login({name: "နုနုနွယ်", isLogin: true})}>အကောင့်ဝင်ရန်</button>
            </>
        }
        <div>
            <h1>Current theme is: {theme.theme}</h1>
            <button disabled={theme.theme === "light"} onClick={() => setTheme({theme: "dark"})}>
                Light Theme
            </button>

            <button disabled={theme.theme == "dark"} onClick={() => setTheme({theme: "light"})}>
                Dark Theme
            </button>
        </div>
    </div>
  )
}

export default ProfilePage;
