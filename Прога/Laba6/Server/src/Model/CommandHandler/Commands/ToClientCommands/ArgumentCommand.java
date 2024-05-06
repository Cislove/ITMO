package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.ToClientCommands.Pair;
import View.RequestLogic.Request;
import View.ResponseLogic.Response;

import java.util.List;


/**
 * Интерфейс для взаимодействия с аргументными командами
 * @author Ильнар Рахимов
 */
public interface ArgumentCommand {
    Pair<Integer, Response> execute(List<Object> requests);
    void update();
}
