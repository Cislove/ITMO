package Model.CommandHandler.Commands;

import Model.LoginAndPassword;
import Model.RequestLogic.Request;
import Model.Storage.StorageObject.StudyGroup;
import Model.NetworkLogic.Handler;
import Model.Storage.StorageObject.StudyGroupWithUser;

import java.io.IOException;
import java.util.LinkedList;
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
        LinkedList<Object> args = new LinkedList<>();
        args.add(LoginAndPassword.login);
        args.add(LoginAndPassword.password);
        try {
            List<StudyGroupWithUser> collection = (List<StudyGroupWithUser>) server.sendRequestAndGetResponse(new Request("show", args));
            if(collection.isEmpty()){
                response.append("В коллекции отсутствуют элементы\n");
            }
            for(StudyGroupWithUser el: collection){
                response.append("ID = ").append(el.group.getId()).append(":").append(" 1\n");
            }
            return new Pair<>(0, response.toString());
        } catch (IOException | ClassNotFoundException e) {
            return new Pair<>(0, "Сервер временно не доступен!\n Попробуйте позже\n");
        }
    }
}
