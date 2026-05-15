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
        <div className="flex flex-row">
            <div className="index bg-blue-200 font-bold text-3xl text-blue-900 shadow-md shadow-slate-600">
                <p className="-translate-y-[0.5]">{index + 1}</p>
            </div>
            <div className="Card bg-blue-200 flex-row flex-auto max-w-200 font-bold text-lg text-blue-900 shadow-md shadow-slate-600">
                <p className="max-w-28">Instructor: <p className="w-28 font-normal">{Lesson.instructor.fullName}</p></p>
                <p className="max-w-34">Vehicle Type: <p className="w-34 font-normal">{Lesson.vehicleType}</p></p>
                <p className="max-w-28">Date: <p className="w-28 font-normal">{Lesson.lessonDate}</p></p>
                <p className="max-w-28">Grade: <p className="w-28 font-normal">{(Lesson.grade != "\u0000") ? Lesson.grade : "Ungraded"}</p></p>
                <p className="max-w-24">Feedback: <p className="w-28 font-normal">{(!!Lesson.feedback) ? Lesson.feedback : "No Feedback"}</p></p>
            </div>
        </div>
    )
}

export default LessonCard