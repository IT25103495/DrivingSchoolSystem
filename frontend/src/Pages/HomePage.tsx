import Navbar from '../Components/Navbar/Navbar'
import { Link } from 'react-router-dom';
import { LuClipboardPen } from "react-icons/lu";
import { CiLogin } from "react-icons/ci";
import { CiViewList } from "react-icons/ci";
import {useAuth} from "../Context/useAuth";

const HomePage = () => {
    const {user} = useAuth();

    return (
        <div className="w-screen justify-center flex flex-col items-center space-y-6">
            <Navbar/>
            {(user?.role == "INSTRUCTOR") ?
                <Link to="/admin" className="my-0">
                    <h1 className="p-4 pt-2 shadow-lg bg-white rounded-lg w-fit flex justify-center text-blue-400 font-extrabold text-shadow-sm text-6xl">DRIVING SCHOOL SYSTEM</h1>
                </Link>
                :
                <h1 className="p-4 pt-2 shadow-lg bg-white rounded-lg w-fit flex justify-center text-blue-400 font-extrabold text-shadow-sm text-6xl">DRIVING SCHOOL SYSTEM</h1>
            }
            <div className="w-full flex flex-col bg-white shadow-lg h-175 mt-8 items-center justify-start">
                <div className="flex flex-col items-start start text-back space-y-7 w-50 mt-8">
                    <Link
                        to="/register"
                        className="w-full py-3 font-bold text-xl rounded shadow-lg text-white bg-blue-300 hover:opacity-70 flex justify-center items-center"
                    >
                        <LuClipboardPen className="size-10 mr-2 -ml-2"/>
                        Sign up
                    </Link>
                    <Link
                        to="/login"
                        className="w-full py-3 font-bold text-xl rounded shadow-lg text-white bg-blue-300 hover:opacity-70 flex justify-center items-center"
                    >
                        <CiLogin className="size-10 mr-2 -ml-2"/>
                        Log in
                    </Link>
                    { (user?.role == "INSTRUCTOR") ? <Link
                            to="/lessonsInstructor"
                            reloadDocument
                            className="w-full py-3 font-bold text-xl rounded shadow-lg text-white bg-blue-300 hover:opacity-70 flex justify-center items-center"
                        >
                            <CiViewList className="size-10 mr-2 -ml-2"/>
                            View Lessons
                        </Link>
                        :
                        <Link
                            to="/lessons"
                            reloadDocument
                            className="w-full py-3 font-bold text-xl rounded shadow-lg text-white bg-blue-300 hover:opacity-70 flex justify-center items-center"
                        >
                            <CiViewList className="size-10 mr-2 -ml-2"/>
                            View Lessons
                        </Link>
                    }
                    { (user?.role == "STUDENT") ?
                        <Link
                            to="/regLesson"
                            reloadDocument
                            className="w-full py-3 font-bold text-xl rounded shadow-lg text-white bg-blue-300 hover:opacity-70 flex justify-center items-center"
                        >
                            <CiViewList className="size-10 mr-2 -ml-2"/>
                            Register for<br/>Lessons
                        </Link>
                        :
                        <></>
                    }
                </div>
            </div>
        </div>
    )
}

export default HomePage