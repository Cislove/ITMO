package Model.Validation;

import java.lang.reflect.InvocationTargetException;

/**
 * Класс описывающий исключение, выбрасываемое при работе с парсингом ввода пользователя и валидацией данных объекта {@link Model.Storage.StorageObject.StudyGroup}
 * @author Ильнар Рахимов
 */
public class ValidateException extends InvocationTargetException {
    String message;
    public ValidateException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
