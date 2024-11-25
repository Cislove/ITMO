import numpy as np


# Данные из таблицы
Y = np.array([0.96, 0.8, 0.6, 0.3, 1.1])
Z = np.array([7.02, 5.6, 4.2, 1.74, 7.8])
a_value = np.sum(Z * Y) / np.sum(Z**2)
print(a_value)
a = a_value  # Предположим, что коэффициент a = 1
N = len(Y)

# Вычисление формулы
sigma_a = np.sqrt(np.sum((Y - a * Z)**2) / ((N - 1) * np.sum(Z**2)))
print(sigma_a)

delta_a = sigma_a * 2

print(delta_a)

eps_a = delta_a / a * 100
print(eps_a)

####2
h1_0 = 0.2
h2_0 = 0.195
h1 = [0.205, 0.215, 0.225, 0.235, 0.245]
h2 = [0.194, 0.193, 0.191, 0.188, 0.186]

for i in range(5):
    res = ((h1_0 - h1[i]) - (h2_0 - h2[i])) / (1.11 - 0.15)
    print(i + 1, ": sin(a) =", res)
    
# Data from table (corresponding Δt1, Δt2 and average values from table)
dt_values = np.array([0.005, 0.012, 0.022, 0.033, 0.048])
df_dt_values = np.array([0.154, 0.279, 0.527, 0.682, 0.0])  # Added 0.0 for 5th term since it's not provided

# Applying the provided formula
delta_t = np.sqrt(np.sum((df_dt_values * dt_values)**2))
print(delta_t)


print(0,16 / )
    