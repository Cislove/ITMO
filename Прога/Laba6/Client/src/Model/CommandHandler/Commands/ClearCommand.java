package Model.CommandHandler.Commands;

import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Validation.IDHandler;

import java.io.IOException;

/**
 * Класс реализации команды "clear"
 * @author Ильнар Рахимов
 */
public class ClearCommand implements Command {
    Handler server;
    public ClearCommand(Handler server){
        this.server = server;
    }
    @Override
    public Pair<Integer, String> execute() {
        try {
            int response = (int) server.sendRequestAndGetResponse(new Request("clear", null));
            if(response == 1)
                return new Pair<>(0, "Коллекция успешно очищена\n");
            else
                return new Pair<>(0, "При очистке возникли непредвиденные проблемы\n");
        } catch (IOException | ClassNotFoundException e) {
            return new Pair<>(0, "Сервер временно не доступен!\n Попробуйте позже\n");
        }
    }
}
