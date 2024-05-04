package Model.CommandHandler.Commands;


/**
 * Интерфейс для взаимодействия с аргументными командами
 * @author Ильнар Рахимов
 */
public interface ArgumentCommand {
    Pair<Integer, String> execute(String arguments);
    void update();
}
