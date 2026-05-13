import type {StudentPost} from '../Models/Student'

export const makeStudent = (
    fullName: string,
    dob : Date,
    email : string,
    phoneNum : string,
    username : string,
    password : string) =>
{
    const student : StudentPost = {
        fullName : fullName,
        dob : dob,
        email : email,
        phoneNum : phoneNum,
        username : username,
        password : password
    }
    return student;
}