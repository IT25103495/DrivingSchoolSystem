import { useEffect, useState } from 'react'
import Navbar from '../Components/Navbar/Navbar'
import { Link } from 'react-router-dom';
import { LuClipboardPen } from "react-icons/lu";
import { CiLogin } from "react-icons/ci";
import LessonList from "../Components/CardLists/LessonList";
import * as API from "../Services/APIService";
import type {LessonGet} from "../Models/Lesson";
import {useAuth} from "../Context/useAuth";
import * as Yup from "yup"
import { yupResolver } from "@hookform/resolvers/yup"
import { useForm } from 'react-hook-form';
import {gradeLessonAPI} from "../Services/APIService";
import {toast} from "react-toastify"
import { useNavigate } from "react-router-dom";

type GradeFormsInputs = {
    ID: number
    grade: string;
    feedback: string;
}

const validation = Yup.object().shape({
    ID: Yup.number().required("Lesson ID is required"),
    grade: Yup.string().required("Grade is required"),
    feedback: Yup.string().required("feedback is required"),
})

const LessonViewInstructor = () => {
    const [lessonValues, setLessonValues] = useState<LessonGet[]>([]);
    const [Loading, setLoading] = useState<Boolean>()
    const {user} = useAuth();
    const navigate = useNavigate()
    const { register, handleSubmit , setValue, formState: {errors}} = useForm<GradeFormsInputs>({ resolver: yupResolver(validation)})

    const [grade, setGrade] = useState("A");

    useEffect(() => {
        if (user?.role == "STUDENT")
            navigate("/lessons")
        setLoading(true)
        getLessons()
    }, [])

    useEffect(() => {
        setValue("grade", grade)
    }, [grade])

    const handleGrade = (form: GradeFormsInputs) => {
        API.gradeLessonAPI(form.ID, form.grade, form.feedback).then(() => {
            toast.success("Grading submitted")
            getLessons()
        }).
        catch(() => {
            toast.warning("Could not submit grading!")
        })
    }

    const getLessons = () => {
        API.getLessonsByInstructorAPI(user.username)
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
            <h1 className="p-4 pt-2 shadow-lg bg-white rounded-lg w-fit flex justify-center text-blue-400 font-extrabold text-shadow-sm text-6xl">ASSIGNED LESSONS</h1>
            <div className="w-full flex flex-row bg-white shadow-lg flex-grow min-h-screen mt-8 items-left justify-start">
                <div className="flex flex-col items-start text-back space-y-7 mt-5 ml-5 w-screen">
                    <LessonList Lessons={lessonValues} showID = {true}/>
                </div>
                <section className="w-120 mr-5 fixed right-0">
                    <div className="w-full bg-white rounded-lg shadow">
                        <div className="px-6 pb-4 space-y-4">
                            <h1 className="text-5xl font-bold leading-tight tracking-tight text-gray-900">
                                Grade Lesson
                            </h1>
                            <form className="space-y-4 md:space-y-6" onSubmit={handleSubmit(handleGrade)}>
                                <div className="flex flex-row space-x-10">
                                    <div>
                                        <label
                                            htmlFor="ID"
                                            className="block mb-2 text-sm font-medium text-gray-900"
                                        >
                                            Lesson ID
                                        </label>
                                        <input
                                            type="ID"
                                            id="ID"
                                            className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-15 p-2.5"
                                            {...register("ID")}
                                        />
                                        {errors.ID ? <p>{errors.ID.message}</p> : ""}
                                    </div>
                                    <div>
                                        <label
                                            htmlFor="grade"
                                            className="block mb-2 text-sm font-medium text-gray-900"
                                        >
                                            Grade
                                        </label>
                                        <select className="size-10" value={grade} onChange={e => setGrade(e.target.value)}>
                                            <option value="A">A</option>
                                            <option value="B">B</option>
                                            <option value="C">C</option>
                                            <option value="D">D</option>
                                            <option value="E">E</option>
                                            <option value="F">F</option>
                                        </select>
                                        {errors.grade ? <p>{errors.grade.message}</p> : ""}
                                    </div>
                                </div>
                                <div>
                                    <label
                                        htmlFor="feedback"
                                        className="block mb-2 text-sm font-medium text-gray-900"
                                    >
                                        Feedback
                                    </label>
                                    <input
                                        type="feedback"
                                        id="feedback"
                                        className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"
                                        {...register("feedback")}
                                    />
                                    {errors.feedback ? <p>{errors.feedback.message}</p> : ""}
                                </div>
                                <button
                                    type="submit"
                                    className="w-full text-white text-l bg-blue-300 hover:opacity-70 focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
                                >
                                    Grade
                                </button>
                            </form>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    )
}

export default LessonViewInstructor