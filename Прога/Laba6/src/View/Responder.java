package View;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс описывающий приемник запросов пользователя. Имеет полный функционал для превращения запроса в необходимый вид
 * @author Ильнар Рахимов
 */
public class Responder implements sendable{
    private final Scanner scn = new Scanner(System.in);
    /**
     * Функция считывания запросов пользователя
     * @return строку - запрос
     */
    @Override
    public String consoleIn() {
        String str = "";
        try {
            str = scn.nextLine();
        }
        catch (NoSuchElementException e){
            System.out.println("Завершение работы");
            System.exit(0);
        }
        return str;
    }
}
