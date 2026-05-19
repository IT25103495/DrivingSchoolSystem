import Navbar from "../Components/Navbar/Navbar";
import { useMemo, useState } from "react";
import * as Yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";
import { toast } from "react-toastify";
import { useAuth } from "../Context/useAuth";
import type { ProgressGet } from "../Models/Progress";
import {
    createProgressAPI,
    getAllProgressAPI,
    getProgressByStudentIdAPI,
    updateProgressAPI,
} from "../Services/ProgressService";
import ProgressSummary from "../Components/Progress/ProgressSummary";
import ProgressTable from "../Components/Progress/ProgressTable";

type LookupForm = {
    studentId: number;
};

const validation = Yup.object().shape({
    studentId: Yup.number()
        .typeError("Student ID is required")
        .integer("Student ID must be a whole number")
        .positive("Student ID must be greater than 0")
        .required("Student ID is required"),
});

const ProgressPage = () => {
    const { user } = useAuth();

    const canSeeAll = useMemo(
        () => user?.role === "INSTRUCTOR" || user?.role === "ADMIN",
        [user?.role]
    );

    const [loadingLookup, setLoadingLookup] = useState(false);
    const [lookupStudentId, setLookupStudentId] = useState<number | null>(null);
    const [lookupMessage, setLookupMessage] = useState<string | null>(null);

    const [loadingAll, setLoadingAll] = useState(false);
    const [allProgress, setAllProgress] = useState<ProgressGet[]>([]);
    const [selectedProgress, setSelectedProgress] = useState<ProgressGet | null>(null);

    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<LookupForm>({ resolver: yupResolver(validation) });

    const loadStudentProgress = async (studentId: number) => {
        setLoadingLookup(true);
        setLookupStudentId(studentId);
        setLookupMessage(null);

        const res = await getProgressByStudentIdAPI(studentId);
        setLoadingLookup(false);

        if (!res?.data) return;

        if (!res.data.success || !res.data.data) {
            setLookupMessage(res.data.message || "Progress not found");
            return;
        }

        setSelectedProgress(res.data.data);
    };

    const onLookupSubmit = async (form: LookupForm) => {
        await loadStudentProgress(form.studentId);
    };

    const onCreateProgress = async () => {
        if (!lookupStudentId) return;

        const res = await createProgressAPI({ studentId: lookupStudentId });
        if (res?.data?.success) {
            toast.success(res.data.message || "Progress created");
            await loadStudentProgress(lookupStudentId);
        } else if (res?.data) {
            toast.warning(res.data.message || "Could not create progress");
        }
    };

    const onRefreshProgress = async () => {
        if (!lookupStudentId) return;

        const res = await updateProgressAPI({ studentId: lookupStudentId });
        if (res?.data?.success) {
            toast.success(res.data.message || "Progress refreshed");
            await loadStudentProgress(lookupStudentId);
        } else if (res?.data) {
            toast.warning(res.data.message || "Could not refresh progress");
        }
    };

    const onLoadAll = async () => {
        setLoadingAll(true);
        const res = await getAllProgressAPI();
        setLoadingAll(false);

        if (!res?.data) return;
        if (!res.data.success) {
            toast.warning(res.data.message || "Could not fetch progress list");
            return;
        }

        setAllProgress(res.data.data || []);
        if (!selectedProgress && res.data.data?.length) {
            setSelectedProgress(res.data.data[0]);
        }
    };

    return (
        <div className="w-screen flex flex-col items-center space-y-6">
            <Navbar />

            <div className="w-full max-w-5xl px-4 pb-10">
                <h1 className="p-4 pt-2 shadow-lg bg-white rounded-lg w-fit text-blue-400 font-extrabold text-shadow-sm text-5xl sm:text-6xl">
                    Progress
                </h1>

                <div className="w-full rounded-lg bg-white shadow-md p-5">
                    <h2 className="text-2xl font-extrabold text-slate-800">Lookup student</h2>
                    <div className="text-sm text-slate-600">
                        Enter a student ID to fetch their progress.
                    </div>

                    <form
                        className="mt-4 grid grid-cols-1 sm:grid-cols-[1fr_auto] gap-3 items-start"
                        onSubmit={handleSubmit(onLookupSubmit)}
                    >
                        <div className="w-full">
                            <label htmlFor="studentId" className="block mb-2 text-sm font-medium text-gray-900">
                                Student ID
                            </label>
                            <input
                                id="studentId"
                                type="number"
                                inputMode="numeric"
                                className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg block w-full p-2.5"
                                placeholder="e.g. 1"
                                {...register("studentId", { valueAsNumber: true })}
                            />
                            {errors.studentId ? (
                                <p className="mt-1 text-sm text-rose-600">{errors.studentId.message}</p>
                            ) : null}
                        </div>

                        <button
                            type="submit"
                            className="h-[42px] sm:mt-[30px] text-white bg-blue-300 hover:opacity-70 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
                            disabled={loadingLookup}
                        >
                            {loadingLookup ? "Loading..." : "Fetch"}
                        </button>
                    </form>

                    <div className="mt-4 flex flex-col sm:flex-row gap-3">
                        <button
                            type="button"
                            onClick={onRefreshProgress}
                            className="select-none px-6 py-2.5 font-bold rounded text-white bg-blue-300 hover:opacity-70 disabled:opacity-50"
                            disabled={!lookupStudentId || loadingLookup}
                        >
                            Recalculate
                        </button>
                        <button
                            type="button"
                            onClick={onCreateProgress}
                            className="select-none px-6 py-2.5 font-bold rounded text-white bg-blue-300 hover:opacity-70 disabled:opacity-50"
                            disabled={!lookupStudentId || loadingLookup}
                        >
                            Create record
                        </button>
                    </div>

                    {lookupMessage ? (
                        <div className="mt-4 rounded-lg bg-blue-50 border border-blue-100 p-4 text-slate-700">
                            {lookupMessage}
                        </div>
                    ) : null}
                </div>

                <div className="mt-6">
                    {selectedProgress ? (
                        <ProgressSummary progress={selectedProgress} />
                    ) : (
                        <div className="w-full rounded-lg bg-white shadow-md p-5 text-slate-700">
                            No progress loaded yet.
                        </div>
                    )}
                </div>

                {canSeeAll ? (
                    <div className="mt-6">
                        <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-3 mb-3">
                            <div className="text-slate-700 font-semibold">
                                Instructor/Admin view
                            </div>
                            <button
                                type="button"
                                onClick={onLoadAll}
                                className="select-none px-6 py-2.5 font-bold rounded text-white bg-blue-300 hover:opacity-70 disabled:opacity-50"
                                disabled={loadingAll}
                            >
                                {loadingAll ? "Loading..." : "Load all"}
                            </button>
                        </div>

                        {allProgress.length ? (
                            <ProgressTable
                                items={allProgress}
                                selectedProgressId={selectedProgress?.progressId}
                                onSelect={(item) => setSelectedProgress(item)}
                            />
                        ) : (
                            <div className="w-full rounded-lg bg-white shadow-md p-5 text-slate-700">
                                No progress list loaded.
                            </div>
                        )}
                    </div>
                ) : null}
            </div>
        </div>
    );
};

export default ProgressPage;
