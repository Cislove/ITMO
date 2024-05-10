package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Validation.IDHandler;
import Model.ResponseLogic.Response;

/**
 * Класс реализации команды "clear"
 * @author Ильнар Рахимов
 */
public class ClearServerCommand implements ServerCommand {
    IStorage storage;
    IDHandler idHandler;
    public ClearServerCommand(IStorage storage, IDHandler idHandler){
        this.storage = storage;
        this.idHandler = idHandler;
    }
    @Override
    public Pair<Integer, Response> execute() {
        for(StudyGroup el: storage.getAllElements()){
            idHandler.openID(Math.toIntExact(el.getId()));
        }
        storage.clear();
        return new Pair<>(0, new Response(1));
    }
}
