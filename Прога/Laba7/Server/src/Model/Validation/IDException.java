package Model.Validation;

/**
 * Класс описывающий исключения, вызываемое при работе с ID класса {@link Model.Storage.StorageObject.StudyGroup}
 * @author Ильнар Рахимов
 */
public class IDException extends Exception{
    String message;
    public IDException(String message){
        this.message = message;
    }
    public IDException(){

    }

    public String getMessage(){
        return message;
    }
}
