package Model.CommandHandler.Commands;

import Model.LoginAndPassword;
import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;
import Model.Storage.ObjectDescription.baseMetaData;

import java.io.IOException;
import java.util.LinkedList;

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
            LinkedList<Object> args = new LinkedList<>();
            args.add(LoginAndPassword.login);
            args.add(LoginAndPassword.password);
            baseMetaData data = (baseMetaData) server.sendRequestAndGetResponse(new Request("info", args));
            String s = "Информация о коллекции:" + "\n";
            s += "Дата инициализации - " + data.initDate + "\n";
            s += "Тип коллекции - " + data.typeCollection + "\n";
            s += "Размер коллекции - " + data.size + "\n";
            return new Pair<>(0, s);
        } catch (IOException | ClassNotFoundException e) {
            return new Pair<>(0, "Сервер временно не доступен!\n Попробуйте позже\n");
        }
    }
}
