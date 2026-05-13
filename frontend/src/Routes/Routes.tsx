import { type RouteObject, redirect } from 'react-router-dom'
import { UserProvider } from '../Context/useAuth'

import Admin from '../Pages/AdminPage'
import Login from '../Pages/LoginPage'
import Register from '../Pages/RegisterPage'

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
        path: '/register',
        element: <Register/>
    },
    {
        path: '/admin',
        element: <Admin/>
    }
]

export default routes;
