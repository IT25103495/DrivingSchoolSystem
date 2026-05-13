export type StudentGet = {
    fullName: string;
    dob : Date;
    phoneNum : string;
    ID : number;
}

export type StudentPost = {
    fullName: string;
    dob : Date;
    email : string;
    phoneNum : string;
    username : string;
    password : string;
}