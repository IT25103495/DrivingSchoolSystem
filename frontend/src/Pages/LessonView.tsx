import { useEffect, useState } from 'react'
import Navbar from '../Components/Navbar/Navbar'
import { Link } from 'react-router-dom';
import { LuClipboardPen } from "react-icons/lu";
import { CiLogin } from "react-icons/ci";
import LessonList from "../Components/CardLists/LessonList";
import * as API from "../Services/APIService";
import type {LessonGet} from "../Models/Lesson";
import {useAuth} from "../Context/useAuth";
import { useNavigate } from "react-router-dom";

const LessonView = () => {
    const [lessonValues, setLessonValues] = useState<LessonGet[]>([]);
    const [Loading, setLoading] = useState<Boolean>()
    const {user} = useAuth();
    const navigate = useNavigate()

    useEffect(() => {
        if (user?.role == "INSTRUCTOR")
            navigate("/lessonsInstructor")
        setLoading(true)
        getLessons()
    }, [])

    const getLessons = () => {
        API.getLessonsByUserAPI(user.username)
            .then((res : any) => {
                if(res?.data) {
                    setLessonValues(res?.data);
                }
                setLoading(false)
            })
            .catch(() => {
                toast.warning("Could not fetch lessons!")
                setLoading(false)
            })
    }

    return (
        <div className="w-screen justify-center flex flex-col items-center space-y-6">
            <Navbar/>
            <h1 className="p-4 pt-2 shadow-lg bg-white rounded-lg w-fit flex justify-center text-blue-400 font-extrabold text-shadow-sm text-6xl">UPCOMING LESSONS</h1>
            <div className="w-full flex flex-col bg-white shadow-lg flex-grow min-h-screen mt-8 items-left justify-start">
                <div className="flex flex-col items-start text-back space-y-7 mt-5 ml-5 w-screen">
                    <LessonList Lessons={lessonValues}/>
                </div>
            </div>
        </div>
    )
}

export default LessonView