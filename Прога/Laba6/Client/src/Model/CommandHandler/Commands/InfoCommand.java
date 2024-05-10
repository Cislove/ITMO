package Model.CommandHandler.Commands;

import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;
import Model.Storage.ObjectDescription.baseMetaData;

import java.io.IOException;

/**
 * Класс реализации команды "info"
 * @author Ильнар Рахимов
 */
public class InfoCommand implements Command {
    Handler server;
    public InfoCommand(Handler server){
        this.server = server;
    }
    public Pair<Integer, String> execute(){
        try {
            baseMetaData data = (baseMetaData) server.sendRequestAndGetResponse(new Request("info", null));
            String s = "Информация о коллекции:" + "\n";
            s += "Дата инициализации - " + data.initDate + "\n";
            s += "Тип коллекции - " + data.typeCollection + "\n";
            s += "Размер коллекции - " + data.size + "\n";
            return new Pair<>(0, s);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
            //return new Pair<>(-1, "Сервер временно не доступен!\n");
        }
    }
}
