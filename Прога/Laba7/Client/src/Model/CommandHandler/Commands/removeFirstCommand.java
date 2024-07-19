package Model.CommandHandler.Commands;

import Model.LoginAndPassword;
import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;
import Model.Storage.IStorage;
import Model.Validation.IDHandler;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Класс реализации команды "remove_first"
 * @author Ильнар Рахимов
 */
public class removeFirstCommand implements Command {
    Handler server;
    public removeFirstCommand(Handler server){
        this.server = server;
    }
    @Override
    public Pair<Integer, String> execute(){
        try {
            LinkedList<Object> args = new LinkedList<>();
            args.add(LoginAndPassword.login);
            args.add(LoginAndPassword.password);
            int response = (int) server.sendRequestAndGetResponse(new Request("remove_first", args));
            if(response == 0){
                return new Pair<>(0, "Первый элемент успешно удален!\n");
            }
            else if(response == 1){
                return new Pair<>(0, "В коллекции отсутствуют ваши элементы\n");
            }
            else{
                return new Pair<>(0, "Непредвиденная ошибка\n");
            }
        } catch (IOException | ClassNotFoundException e) {
            return new Pair<>(0, "Сервер временно не доступен!\n Попробуйте позже\n");
        }
    }
}
