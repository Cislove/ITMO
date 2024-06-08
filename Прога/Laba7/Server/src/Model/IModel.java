package Model;

import Model.CommandHandler.Commands.Pair;
import Model.RequestLogic.Request;
import Model.ResponseLogic.Response;

/**
 * Интерфейс взаимодействия с {@link EntryBlock}
 * @author Ильнар Рахимов
 */
public interface IModel {
    public Pair<Integer, String> execute(String request);
    public Response executeServer(Request request);
    public Pair<Integer, String> start();
}

