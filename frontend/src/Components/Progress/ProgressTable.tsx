import type { ProgressGet } from "../../Models/Progress";

type Props = {
    items: ProgressGet[];
    onSelect: (item: ProgressGet) => void;
    selectedProgressId?: number | null;
};

const ProgressTable = ({ items, onSelect, selectedProgressId }: Props) => {
    return (
        <div className="w-full rounded-lg bg-white shadow-md">
            <div className="p-4 border-b border-slate-100">
                <h2 className="text-xl font-extrabold text-slate-800">All Students Progress</h2>
                <div className="text-sm text-slate-600">Click a row to view details.</div>
            </div>

            <div className="overflow-x-auto">
                <table className="min-w-[860px] w-full text-left">
                    <thead className="bg-slate-50 text-slate-700 text-sm">
                        <tr>
                            <th className="p-3">Student</th>
                            <th className="p-3">Completed</th>
                            <th className="p-3">Total</th>
                            <th className="p-3">Pending</th>
                            <th className="p-3">Progress</th>
                            <th className="p-3">Last updated</th>
                        </tr>
                    </thead>
                    <tbody className="text-sm">
                        {items.map((row) => {
                            const isSelected = selectedProgressId === row.progressId;
                            return (
                                <tr
                                    key={row.progressId}
                                    className={`border-t border-slate-100 cursor-pointer hover:bg-blue-50 ${
                                        isSelected ? "bg-blue-50" : "bg-white"
                                    }`}
                                    onClick={() => onSelect(row)}
                                >
                                    <td className="p-3 font-semibold text-slate-800">
                                        #{row.studentId}
                                    </td>
                                    <td className="p-3 text-slate-700">{row.completedLessons}</td>
                                    <td className="p-3 text-slate-700">{row.totalLessons}</td>
                                    <td className="p-3 text-slate-700">{row.pendingLessons}</td>
                                    <td className="p-3 text-slate-700 font-bold">
                                        {row.progressPercentage}%
                                    </td>
                                    <td className="p-3 text-slate-700">{row.lastUpdated}</td>
                                </tr>
                            );
                        })}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default ProgressTable;

