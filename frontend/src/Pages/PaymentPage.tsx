import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import axios from 'axios';
import Navbar from '../Components/Navbar/Navbar';
import { useAuth } from '../Context/useAuth';

const API = 'http://localhost:8080/';

// ─── Types ────────────────────────────────────────────────────────────────────

type VehicleKey = 'LIGHT' | 'HEAVY';
type Step = 'choose' | 'pay' | 'success';

type PackageOption = {
    key: VehicleKey;
    label: string;
    price: number | null;
    tagline: string;
    features: string[];
    accent: string;
    badge?: string;
};

type StudentRecord = {
    id?: number;
    ID?: number;
    username?: string;
};

type PaymentResponse = {
    success: boolean;
    message: string;
};

// ─── Helpers ──────────────────────────────────────────────────────────────────

const fmt = (n: number) =>
    new Intl.NumberFormat('en-LK', {
        style: 'currency',
        currency: 'LKR',
        maximumFractionDigits: 0,
    }).format(n);

const formatCard = (raw: string) =>
    raw.replace(/\D/g, '').slice(0, 16).replace(/(.{4})/g, '$1 ').trim();

const detectScheme = (num: string): string => {
    const d = num.replace(/\s/g, '');
    if (/^4/.test(d)) return 'VISA';
    if (/^5[1-5]/.test(d)) return 'MC';
    if (/^3[47]/.test(d)) return 'AMEX';
    return '';
};

// ─── Card Preview Component ───────────────────────────────────────────────────

const CardPreview = ({
                         cardNum,
                         expiry,
                         name,
                     }: {
    cardNum: string;
    expiry: string;
    name: string;
}) => {
    const scheme = detectScheme(cardNum);
    const stripped = cardNum.replace(/\s/g, '');
    const display = stripped.padEnd(16, 'x00B7').replace(/(.{4})/g, '$1 ').trim();

    return (
        <div
            style={{
                background: 'linear-gradient(135deg, #1e3a5f 0%, #0f2340 60%, #162d4a 100%)',
                borderRadius: '16px',
                padding: '24px',
                color: 'white',
                fontFamily: "'Courier New', monospace",
                boxShadow: '0 20px 60px rgba(0,0,0,0.35)',
                position: 'relative',
                overflow: 'hidden',
                minHeight: '180px',
                width: '100%',
                maxWidth: '360px',
                margin: '0 auto',
                boxSizing: 'border-box',
            }}
        >
            <div
                style={{
                    position: 'absolute', top: '-50%', left: '-50%',
                    width: '200%', height: '200%',
                    background: 'radial-gradient(ellipse at 30% 30%, rgba(255,255,255,0.06) 0%, transparent 60%)',
                    pointerEvents: 'none',
                }}
            />
            <div
                style={{
                    width: 44, height: 34, borderRadius: 6,
                    background: 'linear-gradient(135deg, #e8c96e, #c8a84b)',
                    marginBottom: 20,
                    display: 'flex', alignItems: 'center', justifyContent: 'center',
                }}
            >
                <div style={{ width: 26, height: 20, border: '1.5px solid rgba(0,0,0,0.25)', borderRadius: 3 }} />
            </div>
            <div style={{ fontSize: 18, letterSpacing: 2.5, marginBottom: 18, opacity: 0.95 }}>
                {display}
            </div>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-end' }}>
                <div>
                    <div style={{ fontSize: 9, opacity: 0.55, textTransform: 'uppercase', letterSpacing: 1, marginBottom: 2 }}>
                        Card Holder
                    </div>
                    <div style={{ fontSize: 13, letterSpacing: 1.2, opacity: 0.9 }}>
                        {name.toUpperCase() || 'YOUR NAME'}
                    </div>
                </div>
                <div style={{ textAlign: 'right' }}>
                    <div style={{ fontSize: 9, opacity: 0.55, textTransform: 'uppercase', letterSpacing: 1, marginBottom: 2 }}>
                        Expires
                    </div>
                    <div style={{ fontSize: 13, letterSpacing: 1.5, opacity: 0.9 }}>{expiry || 'MM/YY'}</div>
                </div>
                <div style={{ fontWeight: 900, fontSize: 14, letterSpacing: 1, color: scheme === 'VISA' ? '#1a9fff' : scheme === 'MC' ? '#f66' : '#aaa' }}>
                    {scheme}
                </div>
            </div>
        </div>
    );
};

// ─── Package Card Component ───────────────────────────────────────────────────

const PackageCard = ({
                         pkg,
                         selected,
                         onSelect,
                     }: {
    pkg: PackageOption;
    selected: boolean;
    onSelect: () => void;
}) => (
    <div
        onClick={onSelect}
        style={{
            border: selected ? `2.5px solid ${pkg.accent}` : '2px solid #e5e7eb',
            borderRadius: 18, padding: '28px 24px', cursor: 'pointer',
            background: selected ? `linear-gradient(145deg, ${pkg.accent}0d, white)` : 'white',
            boxShadow: selected ? `0 8px 32px ${pkg.accent}30` : '0 2px 12px rgba(0,0,0,0.07)',
            transition: 'all 0.25s ease', position: 'relative', flex: 1, minWidth: 240,
        }}
    >
        {pkg.badge && (
            <div style={{
                position: 'absolute', top: -14, left: '50%', transform: 'translateX(-50%)',
                background: pkg.accent, color: 'white', fontSize: 11, fontWeight: 700,
                letterSpacing: 1.5, padding: '4px 14px', borderRadius: 99,
                textTransform: 'uppercase', whiteSpace: 'nowrap',
            }}>
                {pkg.badge}
            </div>
        )}
        <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start' }}>
            <div>
                <div style={{
                    display: 'inline-block', background: `${pkg.accent}18`, color: pkg.accent,
                    fontSize: 11, fontWeight: 700, letterSpacing: 1.5, padding: '3px 10px',
                    borderRadius: 6, textTransform: 'uppercase', marginBottom: 10,
                }}>
                    {pkg.label}
                </div>
                <div style={{ fontSize: 13, color: '#6b7280', marginBottom: 18 }}>{pkg.tagline}</div>
            </div>
            <div style={{
                width: 22, height: 22, borderRadius: '50%',
                border: `2px solid ${selected ? pkg.accent : '#d1d5db'}`,
                background: selected ? pkg.accent : 'transparent',
                display: 'flex', alignItems: 'center', justifyContent: 'center',
                flexShrink: 0, marginLeft: 12, transition: 'all 0.2s',
            }}>
                {selected && (
                    <svg width="11" height="9" viewBox="0 0 11 9" fill="none">
                        <path d="M1 4L4 7L10 1" stroke="white" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" />
                    </svg>
                )}
            </div>
        </div>
        <div style={{ marginBottom: 20 }}>
      <span style={{ fontSize: 34, fontWeight: 800, color: '#111827' }}>
        {pkg.price !== null ? fmt(pkg.price) : '…'}
      </span>
            <span style={{ fontSize: 13, color: '#9ca3af', marginLeft: 6 }}>one-time</span>
        </div>
        <ul style={{ listStyle: 'none', padding: 0, margin: 0, display: 'flex', flexDirection: 'column', gap: 8 }}>
            {pkg.features.map((f) => (
                <li key={f} style={{ display: 'flex', alignItems: 'center', gap: 8, fontSize: 13, color: '#374151' }}>
                    <span style={{ color: pkg.accent, fontSize: 16, lineHeight: 1 }}>✓</span>
                    {f}
                </li>
            ))}
        </ul>
    </div>
);

// ─── Shared Styles ────────────────────────────────────────────────────────────

const labelStyle: React.CSSProperties = {
    display: 'block', fontSize: 12, fontWeight: 600,
    color: '#374151', marginBottom: 6, letterSpacing: 0.2,
};

const inputStyle = (hasError: boolean): React.CSSProperties => ({
    width: '100%',
    border: `1.5px solid ${hasError ? '#ef4444' : '#e5e7eb'}`,
    borderRadius: 10, padding: '10px 12px', fontSize: 14,
    color: '#111827', outline: 'none', background: '#fafafa',
    boxSizing: 'border-box', transition: 'border-color 0.15s',
});

const errStyle: React.CSSProperties = { color: '#ef4444', fontSize: 11, marginTop: 4 };

// ─── Main Component ───────────────────────────────────────────────────────────

const PaymentPage = () => {
    const { user } = useAuth();
    const navigate = useNavigate();

    const [step, setStep] = useState<Step>('choose');
    const [selectedKey, setSelectedKey] = useState<VehicleKey | null>(null);
    const [prices, setPrices] = useState<Record<VehicleKey, number | null>>({ LIGHT: null, HEAVY: null });
    const [cardNum, setCardNum] = useState('');
    const [expiry, setExpiry] = useState('');
    const [cvv, setCvv] = useState('');
    const [cardName, setCardName] = useState('');
    const [loading, setLoading] = useState(false);
    const [fieldErrors, setFieldErrors] = useState<Record<string, string>>({});

    const packages: PackageOption[] = [
        {
            key: 'LIGHT', label: 'Light Vehicle', price: prices.LIGHT,
            tagline: 'Cars, vans & motorcycles',
            features: ['Up to 30 scheduled lessons', 'Light vehicle training', 'Theory & practical sessions', 'Progress tracking'],
            accent: '#3b82f6',
        },
        {
            key: 'HEAVY', label: 'Heavy Vehicle', price: prices.HEAVY,
            tagline: 'Trucks, buses & lorries',
            features: ['Up to 45 scheduled lessons', 'Heavy vehicle training', 'Theory & practical sessions', 'Progress tracking', 'Priority instructor pairing'],
            accent: '#f59e0b', badge: 'Most Comprehensive',
        },
    ];

    useEffect(() => {
        const fetchPrices = async () => {
            try {
                const [light, heavy] = await Promise.all([
                    axios.get<number>(`${API}payment/calculate/LIGHT`),
                    axios.get<number>(`${API}payment/calculate/HEAVY`),
                ]);
                setPrices({ LIGHT: light.data, HEAVY: heavy.data });
            } catch {
                setPrices({ LIGHT: 30000, HEAVY: 45000 });
            }
        };
        void fetchPrices();
    }, []);

    const validate = (): Record<string, string> => {
        const errs: Record<string, string> = {};
        const cleaned = cardNum.replace(/\s/g, '');
        if (cleaned.length !== 16) errs.cardNum = 'Card number must be 16 digits.';
        if (!/^\d{2}\/\d{2}$/.test(expiry)) {
            errs.expiry = 'Use MM/YY format.';
        } else {
            const [mm, yy] = expiry.split('/').map(Number);
            const now = new Date();
            if (mm < 1 || mm > 12) {
                errs.expiry = 'Invalid month.';
            } else if (yy + 2000 < now.getFullYear() || (yy + 2000 === now.getFullYear() && mm < now.getMonth() + 1)) {
                errs.expiry = 'Card has expired.';
            }
        }
        if (cvv.length < 3) errs.cvv = 'CVV must be 3-4 digits.';
        if (!cardName.trim()) errs.cardName = 'Name on card is required.';
        return errs;
    };

    const handleSubmit = async () => {
        if (!selectedKey || !user) return;
        const errs = validate();
        if (Object.keys(errs).length > 0) { setFieldErrors(errs); return; }
        setFieldErrors({});
        setLoading(true);

        try {
            let studentID: number | null = null;
            try {
                const allStudents = await axios.get<StudentRecord[]>(`${API}student/getAll`);
                const match = allStudents.data.find((s) => s.username === user.username);
                if (match) studentID = match.id ?? match.ID ?? null;
            } catch {
                toast.warning('Could not resolve student ID. Contact support.');
                setLoading(false);
                return;
            }

            if (!studentID) {
                toast.warning('Student account not found. Please log in again.');
                setLoading(false);
                return;
            }

            const amount = prices[selectedKey] ?? 0;
            const res = await axios.post<PaymentResponse>(`${API}payment/process`, {
                studentID,
                cardNumber: cardNum.replace(/\s/g, ''),
                amount,
            });

            if (res.data.success) {
                setStep('success');
            } else {
                toast.error(res.data.message || 'Payment failed. Check your card details.');
            }
        } catch (err: unknown) {
            const axiosErr = err as { response?: { data?: { message?: string } } };
            toast.error(axiosErr?.response?.data?.message ?? 'Server error. Please try again.');
        } finally {
            setLoading(false);
        }
    };

    const handleCardInput = (v: string) => setCardNum(formatCard(v));
    const handleExpiryInput = (v: string) => {
        const d = v.replace(/\D/g, '').slice(0, 4);
        setExpiry(d.length > 2 ? `${d.slice(0, 2)}/${d.slice(2)}` : d);
    };
    const handleCvvInput = (v: string) => setCvv(v.replace(/\D/g, '').slice(0, 4));

    const selectedPkg = packages.find((p) => p.key === selectedKey);

    return (
        <>
            <Navbar />
            <div style={{ minHeight: 'calc(100vh - 72px)', background: '#f3f4f6', display: 'flex', flexDirection: 'column', alignItems: 'center', padding: '40px 16px 80px' }}>

                {/* Header */}
                <div style={{ textAlign: 'center', marginBottom: 36 }}>
                    <div style={{ display: 'inline-flex', alignItems: 'center', gap: 8, background: '#dbeafe', color: '#1d4ed8', borderRadius: 99, fontSize: 12, fontWeight: 600, padding: '5px 14px', marginBottom: 14, letterSpacing: 0.5 }}>
                        Secure Payment
                    </div>
                    <h1 style={{ fontSize: 'clamp(28px, 5vw, 42px)', fontWeight: 800, color: '#111827', margin: '0 0 8px', letterSpacing: -1 }}>
                        {step === 'choose' ? 'Choose Your Package' : step === 'pay' ? 'Complete Payment' : 'Payment Confirmed!'}
                    </h1>
                    {step === 'choose' && <p style={{ color: '#6b7280', fontSize: 15 }}>Select the package that suits your learning goals</p>}
                    {step === 'pay' && selectedPkg && (
                        <p style={{ color: '#6b7280', fontSize: 15 }}>
                            {selectedPkg.label} — {prices[selectedKey!] !== null ? fmt(prices[selectedKey!]!) : '...'}
                        </p>
                    )}
                </div>

                {/* Step 1: Choose */}
                {step === 'choose' && (
                    <div style={{ width: '100%', maxWidth: 820 }}>
                        <div style={{ display: 'flex', gap: 20, flexWrap: 'wrap', justifyContent: 'center', marginBottom: 32 }}>
                            {packages.map((pkg) => (
                                <PackageCard key={pkg.key} pkg={pkg} selected={selectedKey === pkg.key} onSelect={() => setSelectedKey(pkg.key)} />
                            ))}
                        </div>
                        <div style={{ display: 'flex', justifyContent: 'center' }}>
                            <button
                                onClick={() => { if (!selectedKey) { toast.warning('Please select a package to continue.'); return; } setStep('pay'); }}
                                style={{
                                    background: selectedKey ? (packages.find((p) => p.key === selectedKey)?.accent ?? '#3b82f6') : '#9ca3af',
                                    color: 'white', border: 'none', borderRadius: 12, padding: '14px 48px',
                                    fontSize: 16, fontWeight: 700, cursor: selectedKey ? 'pointer' : 'not-allowed',
                                    transition: 'all 0.2s', boxShadow: selectedKey ? '0 4px 20px rgba(59,130,246,0.35)' : 'none',
                                }}
                            >
                                Continue to Payment
                            </button>
                        </div>
                    </div>
                )}

                {/* Step 2: Pay */}
                {step === 'pay' && (
                    <div style={{ width: '100%', maxWidth: 720, display: 'flex', gap: 28, flexWrap: 'wrap', justifyContent: 'center' }}>
                        <div style={{ flex: '1 1 300px', minWidth: 280 }}>
                            <div style={{ background: 'white', borderRadius: 18, padding: '24px', boxShadow: '0 2px 16px rgba(0,0,0,0.08)', marginBottom: 20 }}>
                                <div style={{ fontSize: 12, fontWeight: 700, color: '#9ca3af', letterSpacing: 1, textTransform: 'uppercase', marginBottom: 16 }}>Order Summary</div>
                                <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: 10 }}>
                                    <span style={{ fontSize: 14, color: '#374151' }}>{selectedPkg?.label} Package</span>
                                    <span style={{ fontWeight: 700, color: '#111827' }}>{prices[selectedKey!] !== null ? fmt(prices[selectedKey!]!) : '...'}</span>
                                </div>
                                <div style={{ borderTop: '1px solid #f3f4f6', margin: '14px 0' }} />
                                <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                                    <span style={{ fontWeight: 700, color: '#111827' }}>Total Due</span>
                                    <span style={{ fontWeight: 800, fontSize: 20, color: selectedPkg?.accent ?? '#3b82f6' }}>
                    {prices[selectedKey!] !== null ? fmt(prices[selectedKey!]!) : '...'}
                  </span>
                                </div>
                            </div>
                            <CardPreview cardNum={cardNum} expiry={expiry} name={cardName} />
                        </div>

                        <div style={{ flex: '1 1 300px', minWidth: 280, background: 'white', borderRadius: 18, padding: '28px 24px', boxShadow: '0 2px 16px rgba(0,0,0,0.08)' }}>
                            <div style={{ fontSize: 12, fontWeight: 700, color: '#9ca3af', letterSpacing: 1, textTransform: 'uppercase', marginBottom: 20 }}>Card Details</div>

                            <div style={{ marginBottom: 16 }}>
                                <label style={labelStyle}>Card Number</label>
                                <input value={cardNum} onChange={(e) => handleCardInput(e.target.value)} placeholder="0000 0000 0000 0000" style={inputStyle(!!fieldErrors.cardNum)} />
                                {fieldErrors.cardNum && <p style={errStyle}>{fieldErrors.cardNum}</p>}
                            </div>

                            <div style={{ marginBottom: 16 }}>
                                <label style={labelStyle}>Name on Card</label>
                                <input value={cardName} onChange={(e) => setCardName(e.target.value)} placeholder="Full Name" style={inputStyle(!!fieldErrors.cardName)} />
                                {fieldErrors.cardName && <p style={errStyle}>{fieldErrors.cardName}</p>}
                            </div>

                            <div style={{ display: 'flex', gap: 12, marginBottom: 24 }}>
                                <div style={{ flex: 1 }}>
                                    <label style={labelStyle}>Expiry Date</label>
                                    <input value={expiry} onChange={(e) => handleExpiryInput(e.target.value)} placeholder="MM/YY" style={inputStyle(!!fieldErrors.expiry)} />
                                    {fieldErrors.expiry && <p style={errStyle}>{fieldErrors.expiry}</p>}
                                </div>
                                <div style={{ flex: 1 }}>
                                    <label style={labelStyle}>CVV</label>
                                    <input value={cvv} onChange={(e) => handleCvvInput(e.target.value)} placeholder="***" type="password" style={inputStyle(!!fieldErrors.cvv)} />
                                    {fieldErrors.cvv && <p style={errStyle}>{fieldErrors.cvv}</p>}
                                </div>
                            </div>

                            <button
                                onClick={() => { void handleSubmit(); }}
                                disabled={loading}
                                style={{
                                    width: '100%', background: loading ? '#9ca3af' : (selectedPkg?.accent ?? '#3b82f6'),
                                    color: 'white', border: 'none', borderRadius: 12, padding: '14px',
                                    fontSize: 15, fontWeight: 700, cursor: loading ? 'not-allowed' : 'pointer',
                                    marginBottom: 12, transition: 'all 0.2s',
                                    boxShadow: loading ? 'none' : '0 4px 20px rgba(59,130,246,0.3)',
                                }}
                            >
                                {loading ? 'Processing...' : `Pay ${prices[selectedKey!] !== null ? fmt(prices[selectedKey!]!) : ''}`}
                            </button>

                            <button
                                onClick={() => setStep('choose')}
                                disabled={loading}
                                style={{ width: '100%', background: 'transparent', border: '1.5px solid #e5e7eb', borderRadius: 12, padding: '11px', fontSize: 14, fontWeight: 600, color: '#6b7280', cursor: 'pointer' }}
                            >
                                Change Package
                            </button>

                            <p style={{ textAlign: 'center', fontSize: 11, color: '#9ca3af', marginTop: 14 }}>
                                Your payment is encrypted and secure
                            </p>
                        </div>
                    </div>
                )}

                {/* Step 3: Success */}
                {step === 'success' && (
                    <div style={{ background: 'white', borderRadius: 24, padding: '52px 40px', maxWidth: 440, width: '100%', textAlign: 'center', boxShadow: '0 8px 48px rgba(0,0,0,0.1)' }}>
                        <div style={{ width: 72, height: 72, borderRadius: '50%', background: 'linear-gradient(135deg, #10b981, #059669)', display: 'flex', alignItems: 'center', justifyContent: 'center', margin: '0 auto 20px', fontSize: 32, boxShadow: '0 8px 24px rgba(16,185,129,0.35)' }}>
                            ✓
                        </div>
                        <h2 style={{ fontSize: 26, fontWeight: 800, color: '#111827', marginBottom: 10 }}>Payment Successful!</h2>
                        <p style={{ color: '#6b7280', fontSize: 14, marginBottom: 8 }}>
                            You have enrolled in the <strong>{selectedPkg?.label}</strong> package.
                        </p>
                        <p style={{ color: '#6b7280', fontSize: 14, marginBottom: 32 }}>
                            Amount paid: <strong style={{ color: '#111827' }}>{prices[selectedKey!] !== null ? fmt(prices[selectedKey!]!) : ''}</strong>
                        </p>
                        <button
                            onClick={() => navigate('/regLesson')}
                            style={{ width: '100%', background: '#3b82f6', color: 'white', border: 'none', borderRadius: 12, padding: '13px', fontSize: 15, fontWeight: 700, cursor: 'pointer', marginBottom: 10, boxShadow: '0 4px 16px rgba(59,130,246,0.3)' }}
                        >
                            Register for Lessons
                        </button>
                        <button
                            onClick={() => navigate('/home')}
                            style={{ width: '100%', background: 'transparent', border: '1.5px solid #e5e7eb', borderRadius: 12, padding: '11px', fontSize: 14, fontWeight: 600, color: '#6b7280', cursor: 'pointer' }}
                        >
                            Back to Home
                        </button>
                    </div>
                )}
            </div>
        </>
    );
};

export default PaymentPage;
