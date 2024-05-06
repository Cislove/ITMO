package Model.CommandHandler.Commands;

import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;

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
            args.add(arguments);
            List<StudyGroup> response = (LinkedList<StudyGroup>) server.sendRequestAndGetResponse(new Request("add_if_min", args));
            if(response.isEmpty()){
                out.append("Таких элементов нет\n");
            }
            else {
                for(StudyGroup el : response){
                    out.append(el);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            return new Pair<>(-1, "Сервер временно не доступен!\n");
        }
        return new Pair<>(0, out.toString());
    }

    @Override
    public void update() {

    }
}
