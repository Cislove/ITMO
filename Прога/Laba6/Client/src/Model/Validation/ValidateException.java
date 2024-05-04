package Model.Validation;

import Client.Model.Storage.StorageObject.StudyGroup;

import java.lang.reflect.InvocationTargetException;

/**
 * Класс описывающий исключение, выбрасываемое при работе с парсингом ввода пользователя и валидацией данных объекта {@link StudyGroup}
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
