package Model.Validation;

import Model.IODriver.XMLParser.ParseException;
import Model.Storage.StorageObject.FormOfEducation;
import Model.Storage.StorageObject.Semester;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Класс описывающий парсер вводимых пользователем данных о группе
 * @author Ильнар Рахимов
 */
public class Parser {
    /**
     * Парсинг name к установленному формату
     * @return name в установленном формате
     */
    public String StudyGroupNameParser(String name) throws ParseException{
        if(name == null){
            throw new ParseException("У группы обязательно должно быть имя!\n");
        }
        return name;
    }
    /**
     * Парсинг studentsCount к установленному формату
     * @return studentsCount в установленном формате
     */
    public Long StudyGroupStudentsCountParser(String studentsCount) throws ParseException{
        try {
            Long res;
            res = Long.parseLong(studentsCount);
            return res;
        }
        catch (NumberFormatException e){
            throw new ParseException("\"Значение количества людей должно быть целым числом в диапазоне (0, 9223372036854775807)\n");
        }
    }
    /**
     * Парсинг formOfEducation к установленному формату
     * @return formOfEducation в установленном формате
     */
    public FormOfEducation StudyGroupFormOfEducationParser(String formOfEducation) throws ParseException{
        if(formOfEducation.isEmpty()){
            return null;
        }
        try {
            return FormOfEducation.valueOf(formOfEducation);
        }
        catch (IllegalArgumentException e) {
            throw new ParseException("Тип обучения группы должен быть обязательно одним из предложенных!\n");
        }
    }
    /**
     * Парсинг SemesterEnum к установленному формату
     * @return SemesterEnum в установленном формате
     */
    public Semester StudyGroupSemesterEnumParser(String semesterEnum) throws ParseException{
        if(semesterEnum.isEmpty()){
            return null;
        }
        try {
            return Semester.valueOf(semesterEnum);
        }
        catch (IllegalArgumentException e){
            throw new ParseException("Тип семестра у группы должен быть одним из предложенных!\n");
        }
    }
    /**
     * Парсинг xCord к установленному формату
     * @return xCord в установленном формате
     */
    public Float CoordinatesXCordParser(String XCord) throws ParseException{
        try {
            Float res;
            res = Float.parseFloat(XCord);
            if(res.equals(Float.POSITIVE_INFINITY) || res.equals(Float.NEGATIVE_INFINITY)){
                throw new ParseException("Значение координаты X должно лежать в диапазоне (-407; 3.4e+38), с не более, чем 8 цифрами после запятой\n");
            }
            return res;
        }
        catch (NumberFormatException e){
            throw new ParseException("Значение координаты X должно быть числом\n");
        }
    }
    /**
     * Парсинг yCord к установленному формату
     * @return yCord в установленном формате
     */
    public Float CoordinatesYCordParser(String YCord) throws ParseException{
        try {
            Float res;
            res = Float.parseFloat(YCord);
            if(res.equals(Float.POSITIVE_INFINITY) || res.equals(Float.NEGATIVE_INFINITY)){
                throw new ParseException("Значение координаты Y должно лежать в диапазоне (-3.4e+38; 3.4e+38), с не более, чем 8 цифрами после запятой\n");
            }
            return res;
        }
        catch (NumberFormatException e){
            throw new ParseException("Значение координаты Y должно быть числом\n");
        }
    }
    /**
     * Парсинг Person.name к установленному формату
     * @return Person.name в установленном формате
     */
    public String PersonNameParser(String name) throws ParseException{
        if(name == null){
            throw new ParseException("У человека обязательно должно быть имя!\n");
        }
        return name;
    }
    /**
     * Парсинг Person.birthday к установленному формату
     * @return Person.birthday в установленном формате
     */
    public LocalDate PersonBirthdayParser(String birthday) throws ParseException{
        if(birthday == null || birthday.isEmpty()){
            throw new ParseException("Дата рождения у человека обязательно должна быть!\n");
        }
        if(Pattern.matches("\\d{2}+.\\d{2}+.\\d{4}+", birthday)){
            return LocalDate.of(Integer.parseInt(birthday.substring(6, 10)),
                    Integer.parseInt(birthday.substring(3, 5)),
                    Integer.parseInt(birthday.substring(0, 2)));
        }
        else{
            throw new ParseException("Дату рождения нужно вводить в формате: ДД.ММ.ГГГГ\n");
        }
    }
    /**
     * Парсинг Person.height к установленному формату
     * @return Person.height в установленном формате
     */
    public Double PersonHeightParser(String height) throws ParseException{
        try {
            Double res;
            res = Double.parseDouble(height);
            if(res.equals(Double.POSITIVE_INFINITY) || res.equals(Double.NEGATIVE_INFINITY)){
                throw new ParseException("Значение роста должно быть целым числом в диапазоне (0, 1.7e+308), с не более, чем 16 цифрами после запятой\n");
            }
            return Double.parseDouble(height);
        }
        catch (NumberFormatException e){
            throw new ParseException("Значение роста должно быть целым числом!\n");
        }
    }
    /**
     * Парсинг Person.weight к установленному формату
     * @return Person.weight в установленном формате
     */
    public Double PersonWeightParser(String weight) throws ParseException{
        try {
            Double res;
            res = Double.parseDouble(weight);
            if(res.equals(Double.POSITIVE_INFINITY) || res.equals(Double.NEGATIVE_INFINITY)){
                throw new ParseException("Значение веса должно быть целым числом в диапазоне (0, 1.7e+308), с не более, чем 16 цифрами после запятой\n");
            }
            return Double.parseDouble(weight);
        }
        catch (NumberFormatException e){
            throw new ParseException("Значение веса должно быть числом!\n");
        }
    }
    /**
     * Парсинг Person.passportID к установленному формату
     * @return Person.passportID в установленном формате
     */
    public String PersonPassportIDParser(String id) throws ParseException{
        if(id == null || id.isEmpty()){
            throw new ParseException("У человека должен быть номер паспорта!\n");
        }
        return id;
    }
}
