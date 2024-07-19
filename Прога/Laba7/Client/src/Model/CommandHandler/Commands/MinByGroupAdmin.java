package Model.CommandHandler.Commands;

import Model.LoginAndPassword;
import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.StudyGroupWithUser;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Класс реализации команды "min_by_group_admin"
 * @author Ильнар Рахимов
 */
public class MinByGroupAdmin implements Command {
    private Handler server;
    public MinByGroupAdmin(Handler server){
        this.server = server;
    }
    @Override
    public Pair<Integer, String> execute(){
        try {
            LinkedList<Object> args = new LinkedList<>();
            args.add(LoginAndPassword.login);
            args.add(LoginAndPassword.password);
            StudyGroupWithUser inst = (StudyGroupWithUser) server.sendRequestAndGetResponse(new Request("min_by_group_admin", args));
            if(inst == null){
                return new Pair<>(0, "Все элементы коллекции без поля groupAdmin\n");
            }
            return new Pair<>(0, inst.toString());
        } catch (IOException | ClassNotFoundException e) {
            return new Pair<>(0, "Сервер временно не доступен!\n Попробуйте позже\n");
        }
    }
}
