package Model.CommandHandler.Commands;

import Model.LoginAndPassword;
import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.StudyGroupWithUser;

import java.io.IOException;
import java.util.LinkedList;
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
            LinkedList<Object> args = new LinkedList<>();
            args.add(LoginAndPassword.login);
            args.add(LoginAndPassword.password);
            List<StudyGroupWithUser> collection = (List<StudyGroupWithUser>) server.sendRequestAndGetResponse(new Request("show", args));
            if(collection.isEmpty()){
                response.append("В коллекции отсутствуют элементы\n");
            }
            for(StudyGroupWithUser el: collection){
                response.append(el.toString());
            }
            return new Pair<>(0, response.toString());
        } catch (IOException | ClassNotFoundException e) {
            return new Pair<>(0, "Сервер временно не доступен!\n Попробуйте позже\n");
        }
    }
}
