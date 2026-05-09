import type { SyntheticEvent } from 'react';
import type { StudentGet } from '../../Models/Student';

interface Props {
    Student: StudentGet;
    index: number;
    onDelete: (e: SyntheticEvent) => void;
}

const StudentCard = ({Student,index, /*onDelete*/}: Props) => {
    return (
        <div>
            <p>{index + 1}) ID: {Student.ID}, Full Name: {Student.fullName}</p>
        </div>
    )
}

export default StudentCard