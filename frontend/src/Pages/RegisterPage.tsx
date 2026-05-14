import React, {useState ,useEffect} from 'react'
import * as Yup from "yup"
import { yupResolver } from "@hookform/resolvers/yup"
import { useAuth } from '../Context/useAuth';
import { useForm } from 'react-hook-form';
import { Link } from 'react-router-dom';
import DatePicker from "react-datepicker";
import {makeStudent} from "../Helpers/ObjectMaker"
import Navbar from '../Components/Navbar/Navbar'

import "../Components/DatePicker/calander.css"

type Props = {}

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

const RegisterPage = (props: Props) => {
    const {registerUser} = useAuth();
    const { register, handleSubmit , setValue, formState: {errors}} = useForm<RegisterFormsInputs>({ resolver: yupResolver(validation)})

    const [dob, setDob] = useState(new Date("2000-01-01"));

    const handleRegister = (form: RegisterFormsInputs) => {
        registerUser(makeStudent(form.fullName,form.dob,form.email,form.phoneNum,form.username,form.password))
    }

    useEffect(() => {
        setValue("dob", dob);
    }, [dob]); // Dependency array

    return (
        <>
            <Navbar/>
            <section className="">
                <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
                    <div className="w-full bg-white rounded-lg shadow md:mb-20 md:mt-20 sm:max-w-md xl:p-0">
                        <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
                            <h1 className="text-xl font-size-md font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
                                Create a new Account
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
                                <p className="text-sm font-light text-gray-500">
                                    Already have an account?{" "}
                                    <Link
                                        to="/login"
                                        className="font-medium text-blue-500 hover:underline"
                                    >
                                        Log in
                                    </Link>
                                </p>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}

export default RegisterPage