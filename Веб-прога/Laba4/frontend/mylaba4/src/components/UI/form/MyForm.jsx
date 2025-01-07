import React, {useRef, useState} from 'react';
import '../../../styles/form.css'

const MyForm = ({onSubmit, changeR, onClick, handleLogout}) => {
    const [xValue, setXValue] = useState(null);
    const [yValue, setYValue] = useState('');
    const [rValue, setRValue] = useState(null);
    const [errors, setErrors] = useState({});

    const [isSubmitting, setIsSubmitting] = useState(false);

    const setR = (r) => {
        setRValue(r);
        changeR(r)
    }

    const validate = () => {
        const newErrors = {};

        if (xValue === null) {
            newErrors.xValue = 'Выберите значение для X.';
        }

        const yNum = parseFloat(yValue);
        if (isNaN(yNum) || yNum < -5 || yNum > 5) {
            newErrors.yValue = 'Введите Y в диапазоне от -5 до 5.';
        }

        if (rValue === null) {
            newErrors.radius = 'Выберите значение для радиуса.';
        }

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (validate()) {
            const pointData = {
                X: xValue,
                Y: parseFloat(yValue),
                radius: rValue,
            };
            setIsSubmitting(true);
            onSubmit(pointData);
            setIsSubmitting(false);
        }
    };

    return (
        <div className="input">
            <h3>Добавить точку</h3>
            <form onSubmit={handleSubmit}>
            <div>
                <label>Координата X:</label>
                <div>
                    {['-4', '-3', '-2', '-1', '0', '1', '2', '3', '4'].map((value) => (
                        <label key={value}>
                            <input
                                type="checkbox"
                                value={value}
                                checked={xValue === value}
                                onChange={() => setXValue(xValue === value ? null : value)}
                            />
                            {value}
                        </label>
                    ))}
                </div>
                {errors.xValue && <div>{errors.xValue}</div>}
            </div>

            <div>
                <label>Координата Y (от -5 до 5):</label>
                <input
                    type="text"
                    value={yValue}
                    onChange={(e) => setYValue(e.target.value)}
                    placeholder="Введите Y"
                />
                {errors.yValue && <div>{errors.yValue}</div>}
            </div>

            <div style={{marginTop: '15px'}}>
                <label>Радиус:</label>
                <div>
                    {['-4', '-3', '-2', '-1', '0', '1', '2', '3', '4'].map((value) => (
                        <label key={value}>
                            <input
                                type="checkbox"
                                value={value}
                                checked={rValue === value}
                                onChange={() => setR(rValue === value ? null : value)}
                            />
                            {value}
                        </label>
                    ))}
                </div>
                {errors.radius && <div>{errors.radius}</div>}
            </div>
            <button type="submit" disabled={isSubmitting}>
                {isSubmitting ? 'Отправка...' : 'Отправить'}
            </button>
                <button type="reset" disabled={isSubmitting} onClick={onClick}>
                    Очистить
                </button>
                <button type="button" disabled={isSubmitting} onClick={handleLogout}>
                    Вернуться в начало
                </button>
            </form>
        </div>
    );
}

export default MyForm;