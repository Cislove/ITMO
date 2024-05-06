package Model.Storage.StorageObject;

import java.io.Serializable;

/**
 * Enum характеризующий тип семестра
 * @author Ильнар Рахимов
 */
public enum Semester implements Serializable {
    THIRD("трехмесячный"),
    FIFTH("пятимесячный"),
    SIXTH("шестимесячный"),
    SEVENTH("семимесячный");
    private final String name;
    Semester(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
