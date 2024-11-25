import numpy as np
import matplotlib.pyplot as plt

# Определение сетки для комплексной плоскости
x = np.linspace(0, 2, 400)  # Вещественная часть от 0 до 2
y = np.linspace(-4, 4, 400)  # Мнимая часть
X, Y = np.meshgrid(x, y)
Z = X + 1j * Y  # Комплексные числа

# Условия
mod_Z = np.abs(Z)
radius_limit = 4 - X
inside_region = (mod_Z <= radius_limit) & (X > 0) & (X < np.pi)

# Построение множества
plt.figure(figsize=(8, 6))
plt.contourf(X, Y, inside_region, levels=[0, 1], colors=['skyblue'], alpha=0.8)
plt.axhline(0, color='black', linewidth=0.5)  # Ось x
plt.axvline(0, color='black', linewidth=0.5)  # Ось y

# Оформление графика
plt.title("Множество D на комплексной плоскости")
plt.xlabel("Вещественная часть Re(z)")
plt.ylabel("Мнимая часть Im(z)")
plt.xlim(-0.5, 3)
plt.ylim(-4.5, 4.5)
plt.grid(True)
plt.show()
