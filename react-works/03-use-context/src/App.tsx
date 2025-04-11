import ProfilePage from "./ProfilePage"
import ThemeProvider from "./ThemeProvider"
import UserProvider from "./UserProvider"

function App() {
  return (
    <>
      <ThemeProvider>
        <UserProvider>
          <ProfilePage />
        </UserProvider>
      </ThemeProvider>
    </>
  )
}

export default App
