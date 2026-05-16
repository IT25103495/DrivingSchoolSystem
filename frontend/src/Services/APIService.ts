import axios from "axios";
import {toast} from "react-toastify";
import type {StudentGet} from "../Models/Student.ts";
import type {InstructorGet} from "../Models/Instructor.ts";
import type {LessonAutoRegisterPost, LessonGet} from "../Models/Lesson";

const API ="http://localhost:8080/"
const SAPI = API + "student/"
const IAPI = API + "instructor/"
const LAPI = API + "lesson/"

///////////////
/// Student ///
///////////////

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

export const getStudentByIdAPI = async (ID: number) => {
    try {
        return await axios.get<StudentGet>(SAPI + `getById?=${ID}`);
    }
    catch (error)
    {
        toast.warning("Could not fetch student! (Service)")
        console.log(error)
    }
}

//TODO: Test if student post works
export const postStudentAPI = async (student: StudentPost) => {
    try {
        return await axios.post<StudentPost>(SAPI + `add`, student);
    } catch (error) {
        toast.warning("Could not post student! (Service)")
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

//////////////////
/// Instructor ///
//////////////////

export const getInstructorsAPI = async () => {
    try {
        return await axios.get<InstructorGet[]>(IAPI + "getAll");
    }
    catch (error)
    {
        toast.warning("Could not fetch instructor list! (Service)")
        console.log(error)
    }
}

export const getInstructorByIdAPI = async (ID: number) => {
    try {
        return await axios.get<InstructorGet>(IAPI + `getById?=${ID}`);
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

//////////////
/// Lesson ///
//////////////

//TODO: test lesson API and see if the objects work
export const getLessonsAPI = async () => {
    try {
        return await axios.get<LessonGet[]>(LAPI + "getAll");
    }
    catch (error)
    {
        toast.warning("Could not fetch lesson list! (Service)")
        console.log(error)
    }
}

export const getLessonsByUserAPI = async (Username: string) => {
    try {
        return await axios.get<LessonGet[]>(LAPI + `getByUser?user=${Username}`);
    }
    catch (error)
    {
        toast.warning("Could not fetch lesson list! (Service, User)")
        console.log(error)
    }
}

export const getLessonByIdAPI = async (ID: number) => {
    try {
        return await axios.get<LessonGet>(LAPI + `getById?=${ID}`);
    }
    catch (error)
    {
        toast.warning("Could not fetch Lesson! (Service)")
        console.log(error)
    }
}

export const autoRegisterLessonAPI = async (vehicleType:string, firstDate:Date, username:string) => {
    try {
        return await axios.post<LessonAutoRegisterPost>(LAPI + 'autoRegister', {
            vehicleType: vehicleType,
            firstDate: firstDate,
            username: username
        })
    }
    catch (error)
    {
        toast.warning("Could not register Lesson! (Service)")
        console.log(error)
    }
}

export const deleteLessonAPI = async (ID: number) => {
    try {
        return await axios.delete<LessonGet>(LAPI + `delete?ID=${ID}`);
    }
    catch (error)
    {
        toast.warning("Could not delete Lesson!")
        console.log(error)
    }
}