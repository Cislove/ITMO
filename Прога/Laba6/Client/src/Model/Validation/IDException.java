package Model.Validation;

import Client.Model.Storage.StorageObject.StudyGroup;

/**
 * Класс описывающий исключения, вызываемое при работе с ID класса {@link StudyGroup}
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
