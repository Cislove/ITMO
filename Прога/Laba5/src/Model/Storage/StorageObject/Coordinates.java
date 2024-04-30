
package Model.Storage.StorageObject;

/**
 * Класс описывающий положение объекта на плоскости(его координаты)
 * @author Ильнар Рахимов
 */
public class Coordinates {
    private Float xCord; //Значение поля должно быть больше -407, Поле не может быть null
    private Float yCord; //Поле не может быть null

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
