import type { ProgressGet } from "../../Models/Progress";
import ProgressBar from "./ProgressBar";
import ProgressStats from "./ProgressStats";

type Props = {
    progress: ProgressGet;
};

const ProgressSummary = ({ progress }: Props) => {
    return (
        <div className="w-full rounded-lg bg-white shadow-md p-5">
            <div className="flex flex-col gap-1 sm:flex-row sm:items-end sm:justify-between">
                <div>
                    <h2 className="text-2xl font-extrabold text-slate-800">
                        Student #{progress.studentId} Progress
                    </h2>
                    <div className="text-sm text-slate-600">
                        Last updated: <span className="font-semibold">{progress.lastUpdated}</span>
                    </div>
                </div>
                <div className="text-sm text-slate-600">
                    Record ID: <span className="font-semibold">{progress.progressId}</span>
                </div>
            </div>

            <div className="mt-5 grid grid-cols-1 lg:grid-cols-3 gap-5 items-start">
                <div className="lg:col-span-2">
                    <ProgressBar percentage={progress.progressPercentage} />
                    <div className="mt-4">
                        <ProgressStats
                            totalLessons={progress.totalLessons}
                            completedLessons={progress.completedLessons}
                            pendingLessons={progress.pendingLessons}
                        />
                    </div>
                </div>

                <div className="rounded-lg bg-blue-50 p-4 border border-blue-100">
                    <div className="text-sm text-slate-700 font-semibold">Quick summary</div>
                    <div className="mt-2 text-slate-700 text-sm leading-relaxed">
                        Completed{" "}
                        <span className="font-bold">{progress.completedLessons}</span> out of{" "}
                        <span className="font-bold">{progress.totalLessons}</span> lessons.
                    </div>
                    <div className="mt-2 text-slate-700 text-sm leading-relaxed">
                        Pending lessons: <span className="font-bold">{progress.pendingLessons}</span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ProgressSummary;

