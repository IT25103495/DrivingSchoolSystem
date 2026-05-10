import axios from "axios";
import {toast} from "react-toastify";
import type {StudentGet} from "../Models/Student.ts";
import type {InstructorGet} from "../Models/Instructor.ts";

const API ="http://localhost:8080/"
const SAPI = API + "student/"
const IAPI = API + "instructor/"
const LAPI = API + "lesson/"

export const getStudentsAPI = async () => {
    try {
        return await axios.get<StudentGet[]>(SAPI + "getAll");
    }
    catch (error)
    {
        toast.warning("Could not fetch student list! (Service)")
        console.log(error)
    }
}

export const getStudentByIdAPI = async () => {
    try {
        return await axios.get<StudentGet[]>(SAPI + `getById?=${ID}`);
    }
    catch (error)
    {
        toast.warning("Could not fetch student! (Service)")
        console.log(error)
    }
}

export const deleteStudentAPI = async (ID: number) => {
    try {
        return await axios.delete<StudentGet>(SAPI + `delete?ID=${ID}`);
    }
    catch (error)
    {
        toast.warning("Could not delete student!")
        console.log(error)
    }
}

export const getInstructorsAPI = async (StudentID: number) => {
    try {
        return await axios.get<InstructorGet[]>(IAPI + "getAll");
    }
    catch (error)
    {
        toast.warning("Could not fetch instructor list! (Service)")
        console.log(error)
    }
}

export const getInstructorByIdAPI = async (StudentID: number) => {
    try {
        return await axios.get<InstructorGet[]>(IAPI + `getById?=${ID}`);
    }
    catch (error)
    {
        toast.warning("Could not fetch instructor! (Service)")
        console.log(error)
    }
}

export const deleteInstructorAPI = async (ID: number) => {
    try {
        return await axios.delete<InstructorGet>(IAPI + `delete?ID=${ID}`);
    }
    catch (error)
    {
        toast.warning("Could not delete Instructor!")
        console.log(error)
    }
}