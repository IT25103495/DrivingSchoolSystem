import LessonCard from '../Cards/LessonCard';
import type { LessonGet } from '../../Models/Lesson';
import type { SyntheticEvent } from 'react';

interface Props {
    Lessons: LessonGet[];
    onDelete: (e: SyntheticEvent) => void;
};

const LessonList = ({Lessons, onDelete} : Props) => {
    return (
        <div className="w-full">
            {Lessons.length > 0? (
                Lessons.map((res, index) => {
                    return (
                        <LessonCard
                            Lesson={res}
                            key={res.lessonID}
                            onDelete={onDelete}
                            index = {index}
                        />
                    );
                })
            ) : (
                <h2>No lessons found (List).</h2>
            )}
        </div>
    )
}

export default LessonList