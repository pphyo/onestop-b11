import { RouterProvider as BaseRouterProvider, createBrowserRouter } from 'react-router'
import { BALANCE_ROUTES } from './routes.config'

export const BalanceRouteProvider = () => {
  return (
    <BaseRouterProvider router={createBrowserRouter(BALANCE_ROUTES)} />
  )
}
