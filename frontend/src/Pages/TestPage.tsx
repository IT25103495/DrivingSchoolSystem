import { useEffect, useState } from 'react'
import { toast } from 'react-toastify';
import { type StudentGet } from '../Models/Student'
import { deleteStudentAPI, getStudentsAPI } from '../Services/APIService';
import StudentList from '../Components/CardLists/StudentList';

const TestPage = () => {
    const [studentValues, setStudentValues] = useState<StudentGet[]>([]);
    const [Loading, setLoading] = useState<Boolean>();

    useEffect(() => {
        setLoading(true)
        getStudents();
    }, [])

    const getStudents = () => {
        getStudentsAPI()
            .then((res : any) => {
                if(res?.data) {
                    setStudentValues(res?.data);
                }
                setLoading(false)
            })
            .catch(() => {
            toast.warning("Could not fetch students!")
            setLoading(false)
        })
    }

    const onStudentDelete = (e:any) => {
        e.preventDefault();
        deleteStudentAPI(e.target[0].value)
            .then((res) => {
                if (res?.status === 200)
                {
                    toast.success("Student deleted")
                    getStudents();
                }
            })
            .catch(() => {
            toast.warning("Error while deleting student")
        })
    }

    return (
        <div>
            {!Loading ? (
                <StudentList Students={studentValues} onDelete={onStudentDelete}/>
            ) : (
                <div>
                    <h2>No students found (Test Page).</h2>
                </div>
                )
            }
        </div>
    )
}

export default TestPage