package Model.CommandHandler.Commands.OnServerCommands;


import Model.CommandHandler.Commands.Pair;

/**
 * Интерфейс для взаимодействия с аргументными командами
 * @author Ильнар Рахимов
 */
public interface ArgumentCommand {
    Pair<Integer, String> execute(String arguments);
    void update();
}