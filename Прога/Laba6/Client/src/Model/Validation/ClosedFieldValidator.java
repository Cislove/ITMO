package Model.Validation;

import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.closedField;

import java.time.LocalDate;

/**
 * Класс описывающий валидатор закрытых({@link closedField}) полей {@link StudyGroup}
 * @author Ильнар Рахимов
 */
public class ClosedFieldValidator extends Validator {
    IDHandler idHandler;
    public ClosedFieldValidator(IStorage storage, IDHandler idHandler) {
        super();
        this.idHandler = idHandler;
    }
    /**
     * Метод реализующий валидацию ID {@link StudyGroup}
     * @return true - ID подходит под требования. Вызов исключения - не подходит под требования.
     * Также в исключение содержится сообщение - в чем именно заключается проблема
     */
    public boolean StudyGroupIDValidation(Long id)throws ValidateException {
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
}
