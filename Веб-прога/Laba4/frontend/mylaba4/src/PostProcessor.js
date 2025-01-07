import React from "react";

const api = {
    sendPoint: async (data) => {
        try {
            console.log(JSON.stringify({x: data.X, y: data.Y, r: data.radius}));
            const response = await fetch('http://localhost:8080/api/points/save_point', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({x: data.X, y: data.Y, r: data.radius}),
            });

            if (!response.ok) {
                throw new Error(`Ошибка записи`);
            }

            return await response.json();
        } catch (error) {
            console.error('Ошибка при отправке точки:', error);
            throw error;
        }
    },

    fetchPoints: async () => {
        try {
            const res = await fetch('http://localhost:8080/api/points/all_points', {
                method: 'GET',
            });
            if (!res.ok) {
                throw new Error('Ошибка получения');
            }
            const data = await res.json();
            return data;
        } catch (error) {
            console.error('Ошибка при получении точек:', error);
            throw error;
        }
    },

    deletePoints: async () => {
        try {
            const res = await fetch('http://localhost:8080/api/points/delete_points', {
                method: 'DELETE',
            });
            if (!res.ok) {
                throw new Error('Ошибка удаления');
            }
        } catch (error) {
            console.error('Ошибка при получении точек:', error);
            throw error;
        }
    }
};

export default api
