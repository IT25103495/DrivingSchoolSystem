import type { SyntheticEvent } from 'react';
import type { LessonGet } from '../../Models/Lesson';
import './Card.css'

interface Props {
    Lesson: LessonGet;
    index: number;
    onDelete: (e: SyntheticEvent) => void;
    showID: boolean
}

const LessonCard = ({Lesson,index, showID /*onDelete*/}: Props) => {
    return (
        <div className="flex flex-row">
            <div className="index bg-blue-200 font-bold text-3xl text-blue-900 shadow-md shadow-slate-600">
                <p className="-translate-y-[0.5]">{index + 1}</p>
            </div>
            <div className="Card bg-blue-200 flex-row flex-auto max-w-200 font-bold text-lg text-blue-900 shadow-md shadow-slate-600">
                {showID ? <div className="max-w-10">ID: <p className="w-10 font-normal">{Lesson.lessonID}</p></div> : <></>}
                <div className="max-w-28">Instructor: <p className="w-28 font-normal">{Lesson.instructor.fullName}</p></div>
                <div className="max-w-34">Vehicle Type: <p className="w-34 font-normal">{Lesson.vehicleType}</p></div>
                <div className="max-w-28">Date: <p className="w-28 font-normal">{Lesson.lessonDate}</p></div>
                <div className="max-w-28">Grade: <p className="w-28 font-normal">{(Lesson.grade != "\u0000") ? Lesson.grade : "Ungraded"}</p></div>
                <div className="max-w-24">Feedback: <p className="w-64 font-normal">{(!!Lesson.feedback) ? Lesson.feedback : "No Feedback"}</p></div>
            </div>
        </div>
    )
}

export default LessonCard