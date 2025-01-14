package Model.Storage.StorageObject;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Класс описывающий человека
 * @author Ильнар Рахимов
 */

public class Person implements Comparable<Person>, Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private LocalDate birthday; //Поле не может быть null
    private Double height; //Поле не может быть null, Значение поля должно быть больше 0
    private Double weight; //Поле не может быть null, Значение поля должно быть больше 0
    private String passportID; //Длина строки должна быть не меньше 9, Значение этого поля должно быть уникальным, Длина строки не должна быть больше 31, Поле не может быть null

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }
    /**
     * Функция получения описание объекта
     * @return Возвращает текстовое описание характеристик объекта
     */
    @Override
    public String toString() {
        String response = "\n";
        response += ("\t\t" + "Имя " + name + "\n");
        response += ("\t\t" + "День рождения " + birthday + "\n");
        response += ("\t\t" + "Рост " + height + "\n");
        response += ("\t\t" + "Вес " + weight + "\n");
        response += ("\t\t" + "ID паспорта " + passportID);
        return response;
    }
    /**
     * Функция сравнения объектов
     * @param o объект для сравнения
     * @return стандартную характеристику сравнения объектов
     */
    @Override
    public int compareTo(Person o) {
        int field;
        if((field = name.compareTo(o.getName())) != 0){
            return field;
        }
        if((field = birthday.compareTo(o.getBirthday())) != 0){
            return field;
        }
        if((field = height.compareTo(o.getHeight())) != 0){
            return field;
        }
        if((field = weight.compareTo(o.getWeight())) != 0){
            return field;
        }
        return 0;
    }
//    @Override
//    public boolean equals(Object o) {
//        if (o == null){
//            return false;
//        }
//        if (!(o instanceof Person)){
//            return false;
//        }
//        return super.equals(o);
//    }
}
