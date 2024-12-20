
package Model.Storage.StorageObject;

import java.io.Serial;
import java.io.Serializable;

/**
 * Класс описывающий положение объекта на плоскости(его координаты)
 * @author Ильнар Рахимов
 */
public class Coordinates implements Serializable {
    @closedField
    @Serial
    private static final long serialVersionUID = 5;
    private Float xCord; //Значение поля должно быть больше -407, Поле не может быть null
    private Float yCord; //Поле не может быть null
    public Coordinates(Float xCord, Float yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
    }
    public Coordinates() {

    }
    public Float getXCord() {
        return xCord;
    }

    public void setXCord(Float xCord) {
        this.xCord = xCord;
    }

    public Float getYCord(){
        return yCord;
    }

    public void setYCord(Float yCord) {
        this.yCord = yCord;
    }
    /**
     * Функция получения описание объекта
     * @return Возвращает текстовое описание характеристик объекта
     */
    @Override
    public String toString() {
        String response = "\n";
        response += ("\t\t" + "по оси X " + xCord.toString() + "\n");
        response += ("\t\t" + "по оси Y " + yCord.toString());
        return response;
    }
}
