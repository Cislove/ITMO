package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.IStorage;
import Model.Validation.IDHandler;
import Model.ResponseLogic.Response;

/**
 * Класс реализации команды "remove_first"
 * @author Ильнар Рахимов
 */
public class removeFirstServerCommand implements ServerCommand {
    IStorage storage;
    IDHandler idHandler;
    public removeFirstServerCommand(IStorage storage, IDHandler idHandler){
        this.storage = storage;
        this.idHandler = idHandler;
    }
    @Override
    public Pair<Integer, Response> execute(){
        if(storage.getElement(0) == null){
            return new Pair<>(0, new Response(1));
        }
        idHandler.openID(Math.toIntExact(storage.getElement(0).getId()));
        storage.delElement(0);
        return new Pair<>(0, new Response(0));
    }
}
