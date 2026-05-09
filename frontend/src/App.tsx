import { Slide, ToastContainer } from 'react-toastify'
import './App.css'
import TestPage from "./Pages/TestPage.tsx";

function App() {
  return (
      <>
        <div>
          <TestPage/>
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
