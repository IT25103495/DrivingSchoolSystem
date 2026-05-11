import type { SyntheticEvent } from 'react';
import type { StudentGet } from '../../Models/Student';
import './Card.css'

interface Props {
    Student: StudentGet;
    index: number;
    onDelete: (e: SyntheticEvent) => void;
}

const StudentCard = ({Student,index, /*onDelete*/}: Props) => {
    return (
        <div className="flex">
            <div className={"index"}>
                <p>{index + 1})</p>
            </div>
            <div className={"Card"}>
                <p>ID: {Student.ID}, Full Name: {Student.fullName}</p>
            </div>
        </div>
    )
}

export default StudentCard