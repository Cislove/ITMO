package org.example;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        // Получаем текущую дату и время
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Форматируем дату и время
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Преобразуем дату и время в строку
        String formattedDateTime = currentDateTime.format(formatter);

        // Выводим результат
        System.out.println("Текущая дата и время: " + formattedDateTime);
    }
}