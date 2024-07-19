package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.ResponseLogic.Response;
import Model.Storage.StorageObject.User;

/**
 * Интерфейс для взаимодействия с безаргументными командами
 * @author Ильнар Рахимов
 */
public interface ServerCommand {
    Pair<Integer, Response> execute(User user);
}
