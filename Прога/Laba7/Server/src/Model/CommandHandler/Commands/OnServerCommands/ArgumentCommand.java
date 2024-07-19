package Model.CommandHandler.Commands.OnServerCommands;


import Model.CommandHandler.Commands.Pair;
import Model.Storage.StorageObject.User;

/**
 * Интерфейс для взаимодействия с аргументными командами
 * @author Ильнар Рахимов
 */
public interface ArgumentCommand {
    Pair<Integer, String> execute(User user, String arguments);
    void update();
}