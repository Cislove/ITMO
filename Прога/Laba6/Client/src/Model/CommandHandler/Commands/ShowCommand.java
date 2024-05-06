package Model.CommandHandler.Commands;

import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;

import java.io.IOException;
import java.util.List;


/**
 * Класс реализации команды "show"
 * @author Ильнар Рахимов
 */
public class ShowCommand implements Command {
    Handler server;
    public ShowCommand(Handler server){
        this.server = server;
    }
    @Override
    public Pair<Integer, String> execute() {
        StringBuilder response = new StringBuilder();
        try {
            List<StudyGroup> collection = (List<StudyGroup>) server.sendRequestAndGetResponse(new Request("show", null));
            if(collection.isEmpty()){
                response.append("В коллекции отсутствуют элементы\n");
            }
            for(StudyGroup el: collection){
                response.append(el.toString());
            }
            return new Pair<>(0, response.toString());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
