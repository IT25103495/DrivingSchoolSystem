import axios from "axios";
import { handleError } from "../Helpers/ErrorHandler";
import type { ApiResponse, ProgressCreatePost, ProgressGet, ProgressUpdatePost } from "../Models/Progress";

const api = "http://localhost:8080/progress";

export const getProgressByStudentIdAPI = async (studentId: number) => {
    try {
        return await axios.get<ApiResponse<ProgressGet | null>>(`${api}/student/${studentId}`);
    } catch (error) {
        handleError(error);
    }
};

export const getAllProgressAPI = async () => {
    try {
        return await axios.get<ApiResponse<ProgressGet[]>>(`${api}/all`);
    } catch (error) {
        handleError(error);
    }
};

export const createProgressAPI = async (payload: ProgressCreatePost) => {
    try {
        return await axios.post<ApiResponse<ProgressGet | null>>(`${api}/add`, payload);
    } catch (error) {
        handleError(error);
    }
};

export const updateProgressAPI = async (payload: ProgressUpdatePost) => {
    try {
        return await axios.put<ApiResponse<ProgressGet | null>>(`${api}/update`, payload);
    } catch (error) {
        handleError(error);
    }
};

export const deleteProgressAPI = async (progressId: number) => {
    try {
        return await axios.delete<ApiResponse<string | null>>(`${api}/delete/${progressId}`);
    } catch (error) {
        handleError(error);
    }
};

