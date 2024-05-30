package Model.Validation;

import Model.Storage.IStorage;

import java.time.LocalDate;

/**
 * Класс описывающий валидатор закрытых({@link Model.Storage.StorageObject.closedField}) полей {@link Model.Storage.StorageObject.StudyGroup}
 * @author Ильнар Рахимов
 */
public class ClosedFieldValidator extends Validator{
    IDHandler idHandler;
    public ClosedFieldValidator(IStorage storage, IDHandler idHandler) {
        super(storage);
        this.idHandler = idHandler;
    }
    /**
     * Метод реализующий валидацию ID {@link Model.Storage.StorageObject.StudyGroup}
     * @return true - ID подходит под требования. Вызов исключения - не подходит под требования.
     * Также в исключение содержится сообщение - в чем именно заключается проблема
     */
    public boolean StudyGroupIDValidation(Long id)throws ValidateException{
        try {
            if(id == null){
                throw new ValidateException("ID должен быть числом\n");
            }
            idHandler.setId(Integer.parseInt(String.valueOf(id)));
        }
        catch (IDException e){
            throw new ValidateException("Данный ID уже занят\n");
        }
        return true;
    }
    /**
     * Метод реализующий валидацию даты создания {@link Model.Storage.StorageObject.StudyGroup}
     * @return true - дата создания подходит под требования. Вызов исключения - не подходит под требования.
     * Также в исключение содержится сообщение - в чем именно заключается проблема
     */
    public boolean StudyGroupCreationDateValidation(LocalDate creationDate)throws ValidateException{
        if(creationDate == null){
            throw new ValidateException("У группы отсутствует дата создания\n");
        }
        return true;
    }
}
