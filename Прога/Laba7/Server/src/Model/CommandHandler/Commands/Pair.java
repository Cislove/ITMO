package Model.CommandHandler.Commands;

/**
 * Класс структура данных "Пара". Описывает ответ классов команд для {@link Model.CommandHandler.Switcher}
 * @author Ильнар Рахимов
 */
public class Pair<T, U>{
    private T left;
    private U right;
    public Pair(T left, U right){
        this.left = left;
        this.right = right;
    }
    public T getLeft() {
        return left;
    }

    public U getRight() {
        return right;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public void setRight(U right) {
        this.right = right;
    }
}
