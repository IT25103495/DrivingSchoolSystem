export type InstructorGet = {
    fullName: string;
    dob : Date;
    phoneNum : string;
    ID : number;
}

export type InstructorPost = {
    fullName: string;
    dob : Date;
    email : string;
    phoneNum : string;
    username : string;
    password : string;
}