package Model.CommandHandler.Commands;

import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;

import java.io.IOException;

/**
 * Класс реализации команды "head"
 * @author Ильнар Рахимов
 */
public class HeadCommand implements Command {
    Handler server;
    public HeadCommand(Handler server){
        this.server = server;
    }

    @Override
    public Pair<Integer, String> execute(){
        try {
            StudyGroup response = (StudyGroup) server.sendRequestAndGetResponse(new Request("head", null));
            if(response == null)
                return new Pair<>(0, "Коллекция пустая\n");
            return new Pair<>(0, response.toString());
        } catch (IOException | ClassNotFoundException e) {
            return new Pair<>(-0, "Возникли непредвиденные проблемы, пожалуйста попробуйте позже\n");
        }
    }
}
