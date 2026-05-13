import { type RouteObject, redirect } from 'react-router-dom'
import { UserProvider } from '../Context/useAuth'

import Admin from '../Pages/AdminPage'
import Login from '../Pages/LoginPage'

const routes: RouteObject[] = [
    {
        path: '/',
        loader: () => redirect("/login")
    },
    {
        path: '/login',
        element: <Login/>
    },
    {
        path: '/admin',
        element: <Admin/>
    }
]

export default routes;
