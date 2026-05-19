type Props = {
    percentage: number;
};

const clamp = (value: number) => Math.max(0, Math.min(100, Math.round(value)));

const ProgressBar = ({ percentage }: Props) => {
    const value = clamp(percentage);

    const color =
        value >= 70 ? "bg-emerald-400" : value >= 35 ? "bg-amber-400" : "bg-rose-400";

    return (
        <div className="w-full">
            <div className="flex items-center justify-between text-sm text-slate-600">
                <span className="font-semibold text-slate-700">Progress</span>
                <span className="font-bold text-slate-800">{value}%</span>
            </div>
            <div className="mt-2 h-3 w-full rounded-full bg-slate-200">
                <div
                    className={`h-3 rounded-full ${color}`}
                    style={{ width: `${value}%` }}
                    aria-label={`Progress ${value}%`}
                    role="progressbar"
                    aria-valuenow={value}
                    aria-valuemin={0}
                    aria-valuemax={100}
                />
            </div>
        </div>
    );
};

export default ProgressBar;

