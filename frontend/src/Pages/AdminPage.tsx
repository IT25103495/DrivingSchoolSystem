import { useEffect, useState } from 'react'
import { toast } from 'react-toastify';
import { type StudentGet, type StudentPost } from '../Models/Student'
import { type InstructorGet, type InstructorPost } from '../Models/Instructor'
import { type LessonGet, type LessonPost } from '../Models/Lesson'
import * as API from '../Services/APIService';
import StudentList from '../Components/CardLists/StudentList';
import LessonList from '../Components/CardLists/LessonList';
import axios from "axios"
import { useAuth } from "../Context/useAuth";
import { useForm } from 'react-hook-form';
import * as Yup from "yup"
import { yupResolver } from "@hookform/resolvers/yup"
import DatePicker from "react-datepicker";
import Navbar from '../Components/Navbar/Navbar'
import {makeInstructor} from "../Helpers/ObjectMaker";
import { Link } from 'react-router-dom';

type RegisterFormsInputs = {
    email:string;
    username: string;
    password: string;
    phoneNum: string;
    fullName: string;
    dob: Date;
}
const maxYearGap = new Date();
maxYearGap.setFullYear(maxYearGap.getFullYear() - 18);

const validation = Yup.object().shape({
    email: Yup.string().required("Email Address is required"),
    username: Yup.string().required("Username is required"),
    password: Yup.string().required("Password is required"),
    phoneNum: Yup.string().required("Phone Number is required"),
    fullName: Yup.string().required("Full Name is required"),
    dob: Yup.date().required("Date of Birth is required").max(maxYearGap, 'Must be at least 18 years old to register'),
})


const TestPage = () => {
    const [studentValues, setStudentValues] = useState<StudentGet[]>([]);
    const [instructorValues, setInstructorValues] = useState<InstructorGet[]>([]);
    const [lessonValues, setLessonValues] = useState<LessonGet[]>([]);
    const [Loading, setLoading] = useState<Boolean>()
    const {isLoggedIn, user, logout, registerInstructor } = useAuth();

    const { register, handleSubmit , setValue, formState: {errors}} = useForm<RegisterFormsInputs>({ resolver: yupResolver(validation)})

    const [dob, setDob] = useState(new Date("2000-01-01"));

    const handleRegister = (form: RegisterFormsInputs) => {
        registerInstructor(makeInstructor(form.fullName,form.dob,form.email,form.phoneNum,form.username,form.password))
    }

    useEffect(() => {
        setValue("dob", dob);
    }, [dob]); // Dependency array

    useEffect(() => {
        setLoading(true)
        getStudents();
        getInstructors()
        setValue("dob", dob);
    }, [])

    const getStudents = () => {
        API.getStudentsAPI()
            .then((res : any) => {
                if(res?.data) {
                    setStudentValues(res?.data);
                }
                setLoading(false)
            })
            .catch(() => {
                toast.warning("Could not fetch students!")
                setLoading(false)
            })
    }

    const onStudentDelete = (e:any) => {
        e.preventDefault();
        API.deleteStudentAPI(e.target[0].value)
            .then((res) => {
                if (res?.status === 200)
                {
                    toast.success("Student deleted")
                    getStudents();
                }
            })
            .catch(() => {
                toast.warning("Error while deleting student")
            })
    }

    const getInstructors = () => {
        API.getInstructorsAPI()
            .then((res : any) => {
                if(res?.data) {
                    setInstructorValues(res?.data);
                }
                setLoading(false)
            })
            .catch(() => {
                toast.warning("Could not fetch instructors!")
                setLoading(false)
            })
    }

    const onInstructorDelete = (e:any) => {
        e.preventDefault();
        API.deleteInstructorAPI(e.target[0].value)
            .then((res) => {
                if (res?.status === 200)
                {
                    toast.success("Instructor deleted")
                    getInstructors();
                }
            })
            .catch(() => {
                toast.warning("Error while deleting instructor")
            })
    }

    return (
        <div>
            <Navbar/>
            <div className="ml-5">
                <h1 className="text-3xl font-bold">ADMIN PAGE (DEV ONLY)</h1>
                <div>
                    {!Loading ? (
                        <div>
                            <StudentList Students={studentValues} onDelete={onStudentDelete}/>
                            <StudentList Students={instructorValues} onDelete={onInstructorDelete}/>
                        </div>
                    ) : (
                        <div>
                            <h2>No students found (Admin Page).</h2>
                        </div>
                    )
                    }
                </div>
                <Link to="/lessonsInstructor" className="text-blue-500 font-bold text-lg underline">
                    Instructor Lesson View
                </Link>
            </div>
            <section className="">
                <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
                    <div className="w-full bg-white rounded-lg shadow md:mb-20 md:mt-20 sm:max-w-md xl:p-0">
                        <div className="px-6 pb-4 space-y-4 md:space-y-6">
                            <h1 className="text-5xl font-size-md font-bold leading-tight tracking-tight text-gray-900">
                                Register New Instructor
                            </h1>
                            <form className="space-y-4 md:space-y-6" onSubmit={handleSubmit(handleRegister)}>
                                <div>
                                    <label
                                        htmlFor="fullName"
                                        className="block mb-2 text-sm font-medium text-gray-900"
                                    >
                                        Full Name
                                    </label>
                                    <input
                                        type="text"
                                        id="fullName"
                                        className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"
                                        placeholder="John Doe"
                                        {...register("fullName")}
                                    />
                                    {errors.fullName ? <p>{errors.fullName.message}</p> : ""}
                                </div>
                                <div>
                                    <label
                                        htmlFor="email"
                                        className="block mb-2 text-sm font-medium text-gray-900"
                                    >
                                        Email Address
                                    </label>
                                    <input
                                        type="text"
                                        id="email"
                                        className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"
                                        placeholder="example@website.com"
                                        {...register("email")}
                                    />
                                    {errors.username ? <p>{errors.username.message}</p> : ""}
                                </div>
                                <div>
                                    <label
                                        htmlFor="phoneNum"
                                        className="block mb-2 text-sm font-medium text-gray-900"
                                    >
                                        Phone Number
                                    </label>
                                    <input
                                        type="text"
                                        id="phoneNum"
                                        className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"
                                        placeholder="XXX XXX XXXX"
                                        {...register("phoneNum")}
                                    />
                                    {errors.username ? <p>{errors.username.message}</p> : ""}
                                </div>
                                <div>
                                    <label
                                        htmlFor="username"
                                        className="block mb-2 text-sm font-medium text-gray-900"
                                    >
                                        Username
                                    </label>
                                    <input
                                        type="text"
                                        id="username"
                                        className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"
                                        placeholder="Username"
                                        {...register("username")}
                                    />
                                    {errors.username ? <p>{errors.username.message}</p> : ""}
                                </div>
                                <div>
                                    <label
                                        htmlFor="password"
                                        className="block mb-2 text-sm font-medium text-gray-900"
                                    >
                                        Password
                                    </label>
                                    <input
                                        type="password"
                                        id="password"
                                        placeholder="••••••••"
                                        className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"
                                        {...register("password")}
                                    />
                                    {errors.password ? <p>{errors.password.message}</p> : ""}
                                </div>
                                <div>
                                    <label
                                        htmlFor="dob"
                                        className="block mb-2 text-sm font-medium text-gray-900"
                                    >
                                        Date of Birth
                                    </label>
                                    <DatePicker selected={dob} onChange={(date) => setDob(date)}/>
                                    {errors.dob ? <p>{errors.dob.message}</p> : ""}
                                    <label className="flex flex-col text-gray-500 text-xs mt-2">
                                        Arrow Keys: Navigate<br/>
                                        PgUp/PgDown: Change Month<br/>
                                        Shift + PgUp/PgDown: Change Year</label>
                                </div>
                                <button
                                    type="submit"
                                    className="w-full text-white bg-blue-300 hover:opacity-70 focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
                                >
                                    Create Account
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    )
}

export default TestPage