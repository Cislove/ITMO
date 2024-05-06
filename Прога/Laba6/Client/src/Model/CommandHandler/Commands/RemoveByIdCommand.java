package Model.CommandHandler.Commands;

import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Validation.IDHandler;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Класс реализации команды "remove_by_id"
 * @author Ильнар Рахимов
 */
public class RemoveByIdCommand implements ArgumentCommand {
    private Handler server;
    public RemoveByIdCommand(Handler server){
        this.server = server;
    }

    @Override
    public Pair<Integer, String> execute(String arguments){
        //System.out.println(arguments);
        Pair<Integer, String> out = new Pair<>(0, "");
        try {
            int id = Integer.parseInt(arguments);
            LinkedList<Object> args = new LinkedList<>();
            args.add(id);
            int response = (int) server.sendRequestAndGetResponse(new Request("remove_by_id", args));
            out.setLeft(0);
            switch (response){
                case 0:
                    out.setRight("Элемент успешно удален\n");
                    break;
                case 1:
                    out.setRight("ID должен принадлежать элементу коллекции\n");
                    break;
                case 2:
                    out.setRight("Коллекция и так пуста\n");
                    break;
            }
        }
        catch (NumberFormatException e){
            out = new Pair<>(0, "ID должен быть числом!\n");
        }
        catch (IOException | ClassNotFoundException e){
            return new Pair<>(-1, "Сервер временно не доступен!\n");
        }
        return out;
    }

    @Override
    public void update() {

    }
}
