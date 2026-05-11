import { Slide, ToastContainer } from 'react-toastify'
import './App.css'
import TestPage from "./Pages/TestPage";
import AdminPage from "./Pages/AdminPage";

function App() {
  return (
      <>
        <div>
          <AdminPage/>
          <ToastContainer
              theme="dark"
              transition={Slide}
              hideProgressBar={true}
              position='bottom-right'
          />
        </div>
      </>
  )
}

export default App
