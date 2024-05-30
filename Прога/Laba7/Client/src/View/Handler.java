package View;

/**
 * Класс описывающий обработчик запросов к вьюхе от контроллера
 * @author Ильнар Рахимов
 */
public class Handler {
    private final Receiver rec = new Receiver();
    private final Responder res = new Responder();
    /**
     * Метод отправляющий пользователю ответ и считывающий запрос
     * @return строку - запрос
     */
    public String update(String str){
        rec.print(str);
        return res.consoleIn();
    }
    /**
     * Метод отправляющий пользователю ответ
     */
    public void send(String str){
        rec.print(str);
    }
}
