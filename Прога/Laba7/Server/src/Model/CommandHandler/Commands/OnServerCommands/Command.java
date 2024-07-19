package Model.CommandHandler.Commands.OnServerCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.StorageObject.User;

/**
 * Интерфейс для взаимодействия с безаргументными командами
 * @author Ильнар Рахимов
 */
public interface Command{
    Pair<Integer, String> execute(User user);
}