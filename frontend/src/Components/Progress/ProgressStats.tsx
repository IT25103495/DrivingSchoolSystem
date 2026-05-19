import { CiCircleCheck, CiClock2, CiViewList } from "react-icons/ci";
import type { ComponentType } from "react";

type Props = {
    totalLessons: number;
    completedLessons: number;
    pendingLessons: number;
};

const StatCard = ({
    label,
    value,
    Icon,
}: {
    label: string;
    value: number;
    Icon: ComponentType<{ className?: string }>;
}) => (
    <div className="rounded-lg bg-white shadow-md p-4 flex items-center gap-3">
        <div className="rounded-full bg-blue-100 p-2">
            <Icon className="size-7 text-blue-500" />
        </div>
        <div className="min-w-0">
            <div className="text-sm text-slate-600">{label}</div>
            <div className="text-2xl font-extrabold text-slate-800">{value}</div>
        </div>
    </div>
);

const ProgressStats = ({ totalLessons, completedLessons, pendingLessons }: Props) => {
    return (
        <div className="grid grid-cols-1 sm:grid-cols-3 gap-4 w-full">
            <StatCard label="Total lessons" value={totalLessons} Icon={CiViewList} />
            <StatCard label="Completed" value={completedLessons} Icon={CiCircleCheck} />
            <StatCard label="Pending" value={pendingLessons} Icon={CiClock2} />
        </div>
    );
};

export default ProgressStats;
