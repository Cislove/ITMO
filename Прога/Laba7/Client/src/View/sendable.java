package View;

/**
 * Интерфейс взаимодействия с {@link Responder}
 * @author Ильнар Рахимов
 */
public interface sendable {
    /**
     * Функция считывания запросов пользователя
     * @return строку - запрос
     */
    String consoleIn();
}
