package Model.CommandHandler.Commands;

import Model.LoginAndPassword;
import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Validation.IDHandler;

import java.io.IOException;
import java.util.LinkedList;

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
            LinkedList<Object> args = new LinkedList<>();
            args.add(LoginAndPassword.login);
            args.add(LoginAndPassword.password);
            int response = (int) server.sendRequestAndGetResponse(new Request("clear", args));
            if(response == 1)
                return new Pair<>(0, "Все ваши элементы успешно удалены\n");
            else
                return new Pair<>(0, "При очистке возникли непредвиденные проблемы\n");
        } catch (IOException | ClassNotFoundException e) {
            return new Pair<>(0, "Сервер временно не доступен!\n Попробуйте позже\n");
        }
    }
}
