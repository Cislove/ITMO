package Model.Storage.StorageObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

/**
 * Класс описывающий студенческую группу
 * @author Ильнар Рахимов
 */

public class StudyGroup implements Comparable<StudyGroup>{
    @closedField
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    @fieldWithCompoundInput
    private Coordinates coordinates; //Поле не может быть null
    @closedField
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0
    @enumType
    @mayBeNull
    private FormOfEducation formOfEducation; //Поле может быть null
    @enumType
    @mayBeNull
    private Semester semesterEnum; //Поле может быть null
    @fieldWithCompoundInput
    @mayBeNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Person groupAdmin; //Поле может быть null
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public void setCreationDate(){
        this.creationDate = LocalDate.now();
    }

    public Long getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(Long studentsCount) {
        this.studentsCount = studentsCount;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public void setSemesterEnum(Semester semesterEnum) {
        this.semesterEnum = semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    /**
     * Функция получения описание объекта
     * @return Возвращает текстовое описание характеристик объекта
     */
    @Override
    public String toString() {
        String response = "Характеристики группы: " + "\n";
        response += ("  " + "ID " + id.toString() + "\n");
        response += ("  " + "Название " + name + "\n");
        response += ("  " + "Координаты: " + coordinates.toString() + "\n");
        response += ("  " + "Дата создания " + creationDate + "\n");
        response += ("  " + "Количество студентов " + studentsCount + "\n");
        if(formOfEducation != null)
            response += ("  " + "Форма обучения " + formOfEducation + "\n");
        if(semesterEnum != null)
            response += ("  " + "Тип семестра " + semesterEnum + "\n");
        if(groupAdmin != null)
            response += ("  " + "Староста: " + groupAdmin + "\n");
        return response;
    }

    /**
     * Функция сравнения объектов
     * @param o объект для сравнения
     * @return стандартную характеристику сравнения объектов
     */
    @Override
    public int compareTo(StudyGroup o) {
        int field;
        if((field = name.compareTo(o.getName())) != 0){
            return field;
        }
        if((field = studentsCount.compareTo(o.getStudentsCount())) != 0){
            return field;
        }
        return 0;
    }
}
