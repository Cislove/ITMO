package Model.Validation;

import Model.Storage.IStorage;
import Model.Storage.StorageObject.FormOfEducation;
import Model.Storage.StorageObject.Semester;

import java.time.LocalDate;

/**
 * Класс описывающий валидатор не закрытых({@link Model.Storage.StorageObject.closedField}) полей {@link Model.Storage.StorageObject.StudyGroup}
 * @author Ильнар Рахимов
 */
public class Validator {
    IStorage storage;
    public Validator(IStorage storage){
        this.storage = storage;
    }
    /**
     * Валидация name
     * @return true - валидация прошла успешно, иначе исключение с причиной ошибки
     */
    public boolean StudyGroupNameValidation(String name) throws ValidateException{
        nullChecker(name);
        if(name.isEmpty()){
            throw new ValidateException("У группы обязательно должно быть имя!\n");
        }
        return true;
    }
    /**
     * Валидация studentsCount
     * @return true - валидация прошла успешно, иначе исключение с причиной ошибки
     */
    public boolean StudyGroupStudentsCountValidation(Long studentsCount) throws ValidateException{
        nullChecker(studentsCount);
        if(studentsCount <= 0L){
            throw new ValidateException("Количество людей в группе обязательно должно быть больше нуля!\n");
        }
        return true;
    }
    /**
     * Валидация formOfEducation
     * @return true - валидация прошла успешно, иначе исключение с причиной ошибки
     */
    public boolean StudyGroupFormOfEducationValidation(FormOfEducation formOfEducation) throws ValidateException{
        //nullChecker(formOfEducation);
        return true;
    }
    /**
     * Валидация semesterEnum
     * @return true - валидация прошла успешно, иначе исключение с причиной ошибки
     */
    public boolean StudyGroupSemesterEnumValidation(Semester semesterEnum) throws ValidateException{
        //nullChecker(semesterEnum);
        return true;
    }
    /**
     * Валидация xCord
     * @return true - валидация прошла успешно, иначе исключение с причиной ошибки
     */
    public boolean CoordinatesXCordValidation(Float xCord) throws ValidateException{
        nullChecker(xCord);
        if(xCord <= -407f){
            throw new ValidateException("Значение координаты X должно быть больше -407 и меньше 3.4e+38\n");
        }
        return true;
    }
    /**
     * Валидация yCord
     * @return true - валидация прошла успешно, иначе исключение с причиной ошибки
     */
    public boolean CoordinatesYCordValidation(Float yCord) throws ValidateException{
        nullChecker(yCord);
        return true;
    }
    /**
     * Валидация Person.name
     * @return true - валидация прошла успешно, иначе исключение с причиной ошибки
     */
    public boolean PersonNameValidation(String name) throws ValidateException{
        if(name.isEmpty()){
            throw new ValidateException("У человека обязательно должно быть имя!\n");
        }
        return true;
    }
    /**
     * Валидация Person.birthday
     * @return true - валидация прошла успешно, иначе исключение с причиной ошибки
     */
    public boolean PersonBirthdayValidation(LocalDate birthday) throws ValidateException{
        nullChecker(birthday);
        return true;
    }
    /**
     * Валидация Person.height
     * @return true - валидация прошла успешно, иначе исключение с причиной ошибки
     */
    public boolean PersonHeightValidation(Double height) throws ValidateException{
        nullChecker(height);
        if(height <= 0){
            throw new ValidateException("Значение роста должно быть целым числом в диапазоне (0, 1.7e+308), с не более, чем 16 цифрами после запятой!\n");
        }
        return true;
    }
    /**
     * Валидация Person.weight
     * @return true - валидация прошла успешно, иначе исключение с причиной ошибки
     */
    public boolean PersonWeightValidation(Double weight) throws ValidateException{
        nullChecker(weight);
        if(weight <= 0){
            throw new ValidateException("Значение веса должно быть целым числом в диапазоне (0, 1.7e+308), с не более, чем 16 цифрами после запятой!\n");
        }
        return true;
    }
    /**
     * Валидация Person.passportID
     * @return true - валидация прошла успешно, иначе исключение с причиной ошибки
     */
    public boolean PersonPassportIDValidation(String id) throws ValidateException{
        nullChecker(id);
        if(id.length() < 9 || id.length() > 31){
            throw new ValidateException("Длина номера паспорта должна быть не менее 9 и не более 31\n");
        }
        if(storage.checkPassportId(id)){
            throw new ValidateException("Номер паспорта должен быть уникальным!\n");
        }
        return true;
    }
    /**
     * Проверка объекта на null
     */
    public void nullChecker(Object o) throws ValidateException {
        if(o == null){
            throw new ValidateException("Нет обязательной информации\n");
        }
    }
}
