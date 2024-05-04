package View;

public class SerializeException extends Exception{
    String message;
    public SerializeException(){
        message = null;
    }
    public SerializeException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        if(message == null){
            return "Ошибка сериализации: неизвестная ошибка";
        }
        return "Ошибка сериализации: " + message;
    }
}
