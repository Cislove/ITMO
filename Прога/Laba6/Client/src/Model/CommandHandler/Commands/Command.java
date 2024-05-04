package Model.CommandHandler.Commands;

/**
 * Интерфейс для взаимодействия с безаргументными командами
 * @author Ильнар Рахимов
 */
public interface Command{
    Pair<Integer, String> execute();
}
