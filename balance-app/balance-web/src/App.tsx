import AuthProvider from "./hooks/AuthProvider"
import { BalanceRouteProvider } from "./routes/BalanceRouteProvider"

function App() {
  return (
    <AuthProvider>
      <BalanceRouteProvider />
    </AuthProvider>
  )
}

export default App
