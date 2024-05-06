package Model.CommandHandler.Commands;

import Model.RequestLogic.Request;
import Model.Storage.StorageObject.StudyGroup;
import Model.NetworkLogic.Handler;

import java.io.IOException;
import java.util.List;

/**
 * Класс реализации команды "group_counting_by_id"
 * @author Ильнар Рахимов
 */
public class GroupCountingByidCommand implements Command {
    private Handler server;
    public GroupCountingByidCommand(Handler server){
        this.server = server;
    }
    @Override
    public Pair<Integer, String> execute(){
        StringBuilder response = new StringBuilder();
        try {
            List<StudyGroup> collection = (List<StudyGroup>) server.sendRequestAndGetResponse(new Request("show", null));
            if(collection.isEmpty()){
                response.append("В коллекции отсутствуют элементы\n");
            }
            for(StudyGroup el: collection){
                response.append("ID = ").append(el.getId()).append(":").append(" 1\n");
            }
            return new Pair<>(0, response.toString());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
