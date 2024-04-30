package Model.Storage.StorageObject;

/**
 * Enum характеризующий тип семестра
 * @author Ильнар Рахимов
 */
public enum Semester {
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
