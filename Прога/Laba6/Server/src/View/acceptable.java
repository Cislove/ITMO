package View;

import java.io.IOException;

/**
 * Интерфейс взаимодействия с {@link Receiver}
 * @author Ильнар Рахимов
 */
public interface acceptable {
    /**
     * Функция считывания запросов пользователя
     * @return строку - запрос
     */
    String consoleIn() throws IOException;
}
