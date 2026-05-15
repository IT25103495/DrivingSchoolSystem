import { type RouteObject, redirect } from 'react-router-dom'
import ProtectedRoute from './ProtectedRoute'

import Admin from '../Pages/AdminPage'
import Login from '../Pages/LoginPage'
import Register from '../Pages/RegisterPage'
import Home from '../Pages/HomePage'
import Lessons from '../Pages/LessonView'

const routes: RouteObject[] = [
    {
        path: '/',
        loader: () => redirect("/home")
    },
    {
        path: '/home',
        element: <Home/>
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
        path: '/lessons',
        element: <ProtectedRoute><Lessons/></ProtectedRoute>
    },
    {
        path: '/admin',
        element: <ProtectedRoute><Admin/></ProtectedRoute>
    }
]

export default routes;
