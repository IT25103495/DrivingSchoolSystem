import axios from "axios";
import {toast} from "react-toastify";
import type {StudentGet} from "../Models/Student.ts";
import type {InstructorGet} from "../Models/Instructor.ts";

const api ="http://localhost:8080/"
const Sapi = api + "student/"
const Iapi = api + "instructor/"
//const Lapi = api + "lesson/"

export const getStudentsAPI = async () => {
    try {
        return await axios.get<StudentGet[]>(Sapi + "getAll");
    }
    catch (error)
    {
        toast.warning("Could not fetch students (Service)! \n" + error.toString())
        console.log(error)
    }
}

export const deleteStudentAPI = async (ID: number) => {
    try {
        return await axios.delete<StudentGet>(Sapi + `getById?ID=` + {ID});
    }
    catch (error)
    {
        toast.warning("Could not fetch students!")
        console.log(error)
    }
}

export const getInstructorsAPI = async (StudentID: number) => {
    try {
        return await axios.get<InstructorGet[]>(Iapi + `?StudentID=${StudentID}`);
    }
    catch (error)
    {
        toast.warning("Could not fetch instructors!")
        console.log(error)
    }
}

export const deleteInstructorAPI = async (ID: number) => {
    try {
        return await axios.delete<InstructorGet>(Iapi + `${ID}`);
    }
    catch (error)
    {
        toast.warning("Could not fetch Instructor!")
        console.log(error)
    }
}