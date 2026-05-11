import type { SyntheticEvent } from 'react';
import type { LessonGet } from '../../Models/Lesson';
import './Card.css'

interface Props {
    Lesson: LessonGet;
    index: number;
    onDelete: (e: SyntheticEvent) => void;
}

const LessonCard = ({Lesson,index, /*onDelete*/}: Props) => {
    return (
        <div className="flex">
            <div className={"index"}>
                <p>{index + 1})</p>
            </div>
            <div className={"Card"}>
                <p>
                    ID: {Lesson.lessonID},
                    Student: {Lesson.student.fullName},
                    Instructor: {Lesson.instructor.fullName},
                    Date: {Lesson.lessonDate},
                    Vehicle Type: {Lesson.vehicleType}
                </p>
            </div>
        </div>
    )
}

export default LessonCard