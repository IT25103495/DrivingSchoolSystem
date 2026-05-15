import React from "react";
import logo from "./Mylogo.png";
import { Link } from "react-router-dom";
import { useAuth } from "../../Context/useAuth";

interface Props {}

const Navbar = (props: Props) => {
  const {isLoggedIn, user, logout } = useAuth();

  return (
    <nav className="relative mx-auto p-5 shadow-md w-full bg-white">
      <div className="flex items-center justify-between">
        <div className="flex items-center space-x-20 hover:shadow-md hover:outline-1 hover:outline-blue-200">
          <Link to="/home">
            <img src={logo} alt="" />
          </Link>
        </div>
        {isLoggedIn() ?  (
          <div className="hidden lg:flex items-center space-x-6 text-back">
          <div className="text-sky-500">Logged in as: {user?.username}</div>
          <a
            onClick={logout}
            className="px-8 py-3 font-bold rounded text-white bg-blue-300 hover:opacity-70"
          >
            Logout
          </a>
        </div>
        ) : (
          <div className="hidden lg:flex items-center space-x-6 text-back">
          <Link to="/login" className="hover:text-darkBlue">Login</Link>
          <Link
            to="/register"
            className="px-8 py-3 font-bold rounded text-white bg-blue-300 hover:opacity-70"
          >
            Signup
          </Link>
        </div>
        )}
        
      </div>
    </nav>
  );
};

export default Navbar;