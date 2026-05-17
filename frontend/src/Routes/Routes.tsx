import { type RouteObject, redirect } from 'react-router-dom'
import ProtectedRoute from './ProtectedRoute'

import Admin from '../Pages/AdminPage'
import Login from '../Pages/LoginPage'
import Register from '../Pages/RegisterPage'
import Home from '../Pages/HomePage'
import Lessons from '../Pages/LessonView'
import LessonsInstructor from '../Pages/LessonViewInstructor'
import RegLesson from '../Pages/LessonRegister'
import Payment from '../Pages/PaymentPage'

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
        path: '/lessonsInstructor',
        element: <ProtectedRoute><LessonsInstructor/></ProtectedRoute>
    },
    {
        path: '/regLesson',
        element: <ProtectedRoute><RegLesson/></ProtectedRoute>
    },
    {
        path: '/admin',
        element: <ProtectedRoute><Admin/></ProtectedRoute>
    },
    {
        path: '/payment',
        element: <ProtectedRoute><Payment/></ProtectedRoute>
    }
]

export default routes;
