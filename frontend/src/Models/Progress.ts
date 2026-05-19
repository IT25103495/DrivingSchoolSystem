export type ApiResponse<T> = {
    success: boolean;
    message: string;
    data: T;
};

export type ProgressGet = {
    progressId: number;
    studentId: number;
    totalLessons: number;
    completedLessons: number;
    pendingLessons: number;
    progressPercentage: number;
    lastUpdated: string;
};

export type ProgressCreatePost = {
    studentId: number;
};

export type ProgressUpdatePost = {
    studentId: number;
    progressID?: number;
    completedLessons?: number;
};

