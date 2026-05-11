export type StudentGet = {
    fullName: string;
    dob : Date;
    email : string;
    phoneNum : string;
    userName : string;
    password : string;
    ID : number;
}
//TODO: Remove username and password fields, get email from auth object

export type StudentPost = {
    fullName: string;
    dob : Date;
    email : string;
    phoneNum : string;
    userName : string;
    password : string;
}