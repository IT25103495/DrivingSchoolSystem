import StudentCard from '../Cards/StudentCard';
import type { StudentGet } from '../../Models/Student';
import type { SyntheticEvent } from 'react';

interface Props {
    Students: StudentGet[];
    onDelete: (e: SyntheticEvent) => void;
};

const StudentList = ({Students, onDelete} : Props) => {
    return (
        <div className="mt-18 2xl:mt-10">
            <h2>Student List</h2>
            {Students.length > 0? (
                Students.map((res, index) => {
                    return (
                        <StudentCard
                            Student={res}
                            key={res.ID}
                            onDelete={onDelete}
                            index = {index}
                        />
                    );
                })
            ) : (
                <h2>No students found (List).</h2>
            )}
        </div>
    )
}

export default StudentList