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
 * Класс реализации команды "filter_contains_name"
 * @author Ильнар Рахимов
 */
public class FilterContainsNameCommand implements ArgumentCommand {
    private Handler server;
    public FilterContainsNameCommand(Handler server){
        this.server = server;
    }
    @Override
    public Pair<Integer, String> execute(String arguments){
        StringBuilder out = new StringBuilder();
        try {
            LinkedList<Object> args = new LinkedList<>();
            args.add(LoginAndPassword.login);
            args.add(LoginAndPassword.password);
            args.add(arguments);
            List<StudyGroupWithUser> response = (LinkedList<StudyGroupWithUser>) server.sendRequestAndGetResponse(new Request("filter_contains_name", args));
            if(response.isEmpty()){
                out.append("Таких элементов нет\n");
            }
            else {
                for(StudyGroupWithUser el : response){
                    out.append(el);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            return new Pair<>(0, "Сервер временно не доступен!\n Попробуйте позже\n");
        }
        return new Pair<>(0, out.toString());
    }

    @Override
    public void update() {

    }
}
