import axios from "axios";
import { handleError } from "../Helpers/ErrorHandler";
import type { UserProfileToken } from "../Models/User"
import type { StudentPost } from "../Models/Student";

const api="http://localhost:8080/";

export const loginAPI = async (username: string,password: string) => {
    try{
        return await axios.post<UserProfileToken>(api + "auth/login", {
            username: username,
            password: password,
        });
    }
    catch(error)
    {
        handleError(error);
    }
}

export const registerAPI = async (student: StudentPost) => {
    try{
        return await axios.post<StudentPost>(api + "auth/register", student);
    }
    catch(error)
    {
        handleError(error);
    }
}