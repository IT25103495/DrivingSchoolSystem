import { Slide, ToastContainer } from 'react-toastify'
import './App.css'
import TestPage from "./Pages/TestPage";
import AdminPage from "./Pages/AdminPage";
import LoginPage from "./Pages/LoginPage";
import {UserProvider} from './Context/useAuth'
import { BrowserRouter, createBrowserRouter, RouterProvider } from 'react-router-dom';
import routes from "./Routes/Routes";

const router = createBrowserRouter(routes);

function App() {
  return (
        <UserProvider>
            <RouterProvider router={router} />
            <ToastContainer
              theme="dark"
              transition={Slide}
              hideProgressBar={true}
              position='bottom-right'
            />
        </UserProvider>
  )
}

export default App
