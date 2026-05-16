import React, {useState ,useEffect} from 'react'
import * as Yup from "yup"
import { yupResolver } from "@hookform/resolvers/yup"
import { useAuth } from '../Context/useAuth';
import { useForm } from 'react-hook-form';
import { Link } from 'react-router-dom';
import DatePicker from "react-datepicker";
import {makeStudent} from "../Helpers/ObjectMaker"
import Navbar from '../Components/Navbar/Navbar'
import { Bounce, Slide, toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

import "../Components/DatePicker/calander.css"
import {loginAPI} from "../Services/AuthService";
import {autoRegisterLessonAPI} from "../Services/APIService";

type Props = {}

type RegisterFormsInputs = {
    vehicleType : string;
    firstDate : Date;
}

const tomorrow = new Date(Date.now())
tomorrow.setHours(0,0,0,0)
tomorrow.setDate(tomorrow.getDate() + 1);

const validation = Yup.object().shape({
    vehicleType: Yup.string().required("Vehicle type is required"),
    firstDate: Yup.date().required("Date of first lesson is required").min(tomorrow, 'First lesson must be at least 1 day from now'),
})

const LessonRegister = (props: Props) => {
    const { register, handleSubmit , setValue, formState: {errors}} = useForm<RegisterFormsInputs>({ resolver: yupResolver(validation)})
    const { user } = useAuth();

    const [firstDate, setFirstDate] = useState(new Date(Date.now()));
    const [vehicleType, setVehicleType] = useState("Light");

    const navigate = useNavigate();

    const handleRegister = (form: RegisterFormsInputs) => {
        RegisterLesson(form.vehicleType, form.firstDate)
    }

    const RegisterLesson = async (vehicleType:string, firstDate: Date) => {
        await autoRegisterLessonAPI(vehicleType,firstDate,user.username).then((res) => {
            if (res.status == 200) {
                toast.success("Registered for Lessons")
                navigate("/lessons");
            }
            else
                toast.warning("Registration failed", {
                    hideProgressBar: true,
                    closeOnClick: true,
                    transition: Bounce,
                    position: "bottom-right",})
        }).catch((e) => toast.warning("Server error occured", {
            hideProgressBar: true,
            closeOnClick: true,
            transition: Bounce,
            position: "bottom-right",
        }))
    }

    useEffect(() => {
        setValue("firstDate", firstDate);
        setValue("vehicleType", vehicleType)
    }, [firstDate, vehicleType]); // Dependency array

    return (
        <>
            <Navbar/>
            <section className="">
                <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
                    <div className="w-full bg-white rounded-lg shadow md:mb-20 md:mt-20 sm:max-w-md xl:p-0">
                        <div className="px-6 pb-4 space-y-4 md:space-y-6">
                            <h1 className="text-5xl font-size-md font-bold leading-tight tracking-tight text-gray-900">
                                Register for Lessons
                            </h1>
                            <form className="space-y-4 md:space-y-6" onSubmit={handleSubmit(handleRegister)}>
                                <div>
                                    <label
                                        htmlFor="vehicleType"
                                        className="block mb-2 text-sm font-medium text-gray-900"
                                    >
                                        Vehicle Type:
                                    </label>
                                    <select value={vehicleType} onChange={e => setVehicleType(e.target.value)}>
                                        <option value="Light">Light</option>
                                        <option value="Heavy">Heavy</option>
                                    </select>
                                    {errors.vehicleType ? <p>{errors.vehicleType.message}</p> : ""}
                                </div>
                                <div>
                                    <label
                                        htmlFor="firstDate"
                                        className="block mb-2 text-sm font-medium text-gray-900"
                                    >
                                        Schedule first lesson for:
                                    </label>
                                    <DatePicker selected={firstDate} onChange={(date) => setFirstDate(date)}/>
                                    {errors.firstDate ? <p>{errors.firstDate.message}</p> : ""}
                                    <label className="flex flex-col text-gray-500 text-xs mt-2">
                                        Arrow Keys: Navigate<br/>
                                        PgUp/PgDown: Change Month<br/>
                                        Shift + PgUp/PgDown: Change Year</label>
                                </div>
                                <button
                                    type="submit"
                                    className="w-full text-white bg-blue-300 hover:opacity-70 focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
                                >
                                    Register
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}

export default LessonRegister