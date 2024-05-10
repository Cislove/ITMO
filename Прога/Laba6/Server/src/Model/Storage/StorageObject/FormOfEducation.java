package Model.Storage.StorageObject;

import java.io.Serializable;

/**
 * Enum характеризующий тип форму обучения
 * @author Ильнар Рахимов
 */
public enum FormOfEducation implements Serializable {
    DISTANCE_EDUCATION("дистанционная"),
    FULL_TIME_EDUCATION("полная"),
    EVENING_CLASSES("вечерняя");
    private final String name;
    FormOfEducation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
