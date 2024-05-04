package Model.CommandHandler.Commands;

import Client.Model.Storage.IStorage;
import Client.Model.Storage.StorageObject.StudyGroup;
import Client.Model.Validation.IDHandler;

/**
 * Класс реализации команды "clear"
 * @author Ильнар Рахимов
 */
public class ClearCommand implements Command {
    IStorage storage;
    IDHandler idHandler;
    public ClearCommand(IStorage storage, IDHandler idHandler){
        this.storage = storage;
        this.idHandler = idHandler;
    }
    @Override
    public Pair<Integer, String> execute() {
        for(StudyGroup el: storage.getAllElements()){
            idHandler.openID(Math.toIntExact(el.getId()));
        }
        storage.clear();
        return new Pair<>(0, "Коллекция успешно очищена\n");
    }
}
