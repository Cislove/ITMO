package View;

/**
 * Интерфейс взаимодействия с {@link Responder}
 * @author Ильнар Рахимов
 */
public interface sendable {
    /**
     * Метод отправки ответов пользователю
     */
    void ConsolePrint(String s);
    void ClientPrint(byte[] arr);
}
