export type LessonGet = {
    lessonID: number;
    student: StudentGet;
    instructor: InstructorGet;
    vehicleType: string;
    lessonDate: Date;
    grade: string;
    feedback: string;
}

export type LessonPost = {
    studentID: number;
    instructorID: number;
    vehicleType: string;
    lessonDate: Date
}

export type LessonAutoRegisterPost = {
    vehicleType: string;
    firstDate: Date;
    username: string;
}