package Model.CommandHandler.Commands;

import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;
import Model.Storage.IStorage;
import Model.Validation.IDHandler;

import java.io.IOException;

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
            int response = (int) server.sendRequestAndGetResponse(new Request("remove_first", null));
            if(response == 0){
                return new Pair<>(0, "Первый элемент успешно удален!\n");
            }
            else{
                return new Pair<>(0, "Непредвиденная ошибка\n");
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
