package Model.CommandHandler.Commands;

import Model.LoginAndPassword;
import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.StudyGroupWithUser;

import java.io.IOException;
import java.util.LinkedList;

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
            LinkedList<Object> args = new LinkedList<>();
            args.add(LoginAndPassword.login);
            args.add(LoginAndPassword.password);
            StudyGroupWithUser response = (StudyGroupWithUser) server.sendRequestAndGetResponse(new Request("head", args));
            if(response == null)
                return new Pair<>(0, "Коллекция пустая\n");
            return new Pair<>(0, response.toString());
        } catch (IOException | ClassNotFoundException e) {
            return new Pair<>(0, "Сервер временно не доступен!\n Попробуйте позже\n");
        }
    }
}
