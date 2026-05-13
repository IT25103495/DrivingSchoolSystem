import { createContext, useEffect, useState } from "react";
import type { UserProfile } from "../Models/User";
import type { StudentPost } from "../Models/Student";
import { useNavigate } from "react-router-dom";
import { loginAPI, registerAPI } from "../Services/AuthService";
import { Bounce, Slide, toast } from "react-toastify";
import React from "react";
import axios from "axios";

type UserContextType = {
    user: UserProfile | null;
    token: string | null;
    registerUser: (student: StudentPost) => void;
    loginUser: (username: string, password: string) => void;
    logout: () => void;
    isLoggedIn: () => boolean;
}

type Props = { children: React.ReactNode };

const UserContext = createContext<UserContextType>({} as UserContextType);

export const UserProvider = ({children} : Props) => {
    // const navigate = useNavigate();
    const [token, setToken] = useState<string | null>(null);
    const [user, setUser] = useState<UserProfile | null>(null);
    const [isReady, setIsReady] = useState(false);

    useEffect(() => {
        const user = localStorage.getItem("user");
        const token = localStorage.getItem("token"); //local storage is not secure for this
        if(user && token){
            setUser(JSON.parse(user));
            setToken(token);
            axios.defaults.headers.common["Authorization"] = ("Bearer " + token);
        }
        setIsReady(true)
    }, [])

    const registerUser = async (student: StudentPost) => {
        await registerAPI(student).then((res) => {
            if(res) {
                loginUser(student.username, student.password)
                // navigate("/search");
            }
        }).catch((e) => toast.warning("Server error occured", {
                    hideProgressBar: true,
                    closeOnClick: true,
                    transition: Bounce,
                    position: "bottom-right",
                }))
    }

    const loginUser = async (username:string, password: string) => {
        await loginAPI(username, password).then((res) => {
            if(res) {
                localStorage.setItem("token", res?.data.token);
                const userObj = {
                    username: username,
                }
                localStorage.setItem("user", JSON.stringify(userObj))
                setToken(res?.data.token!);
                setUser(userObj!);
                toast.success("Login Success", {
                    hideProgressBar: true,
                    closeOnClick: true,
                    transition: Slide,
                    position: "bottom-right",
                })
                // navigate("/search");
            }
        }).catch((e) => toast.warning("Server error occured", {
                    hideProgressBar: true,
                    closeOnClick: true,
                    transition: Bounce,
                    position: "bottom-right",
                }))
    }

    const isLoggedIn = () => {
        return !!user;
    }

    const logout = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("user")
        setUser(null)
        setToken("")
        // navigate("/")
    }

    return (
        <UserContext.Provider value={{loginUser, user, token, logout, isLoggedIn, registerUser}}>
            {isReady ? children : null}
        </UserContext.Provider>
    )
}

export const useAuth = () => React.useContext(UserContext);

