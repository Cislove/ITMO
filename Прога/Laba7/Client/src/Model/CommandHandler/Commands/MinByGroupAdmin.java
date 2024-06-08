package Model.CommandHandler.Commands;

import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;
import Model.Storage.StorageObject.StudyGroup;

import java.io.IOException;

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
            StudyGroup inst = (StudyGroup) server.sendRequestAndGetResponse(new Request("min_by_group_admin", null));
            if(inst == null){
                return new Pair<>(0, "Все элементы коллекции без поля groupAdmin\n");
            }
            return new Pair<>(0, inst.toString());
        } catch (IOException | ClassNotFoundException e) {
            return new Pair<>(0, "Сервер временно не доступен!\n Попробуйте позже\n");
        }
    }
}
