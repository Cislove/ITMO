package Model.IODriver.XMLParser;

/**
 * Класс исключение, выбрасываемое при ошибках работы с XML файлом
 * @author Ильнар Рахимов
 */
public class ParseException extends Exception {
    String message;
    public ParseException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
