package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.ResponseLogic.Response;

import java.util.List;


/**
 * Интерфейс для взаимодействия с аргументными командами
 * @author Ильнар Рахимов
 */
public interface ServerArgumentCommand {
    Pair<Integer, Response> execute(List<Object> requests);
    void update();
}
