import { Slide, ToastContainer } from 'react-toastify'
import './App.css'
import TestPage from "./Pages/TestPage";
import AdminPage from "./Pages/AdminPage";
import LoginPage from "./Pages/LoginPage";
import {UserProvider} from './Context/useAuth'

function App() {
  return (
      <>
        <UserProvider>
            <LoginPage/>
            <AdminPage/>
            <ToastContainer
              theme="dark"
              transition={Slide}
              hideProgressBar={true}
              position='bottom-right'
            />
        </UserProvider>
      </>
  )
}

export default App
