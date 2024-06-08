package Model.CommandHandler.Commands.OnServerCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.IStorage;
import Model.Validation.IDHandler;

/**
 * Класс реализации команды "remove_first"
 * @author Ильнар Рахимов
 */
public class removeFirstCommand implements Command{
    IStorage storage;
    IDHandler idHandler;
    public removeFirstCommand(IStorage storage, IDHandler idHandler){
        this.storage = storage;
        this.idHandler = idHandler;
    }
    @Override
    public Pair<Integer, String> execute(){
        if(storage.getElement(0) == null){
            return new Pair<>(0, "Коллекция и так пуста\n");
        }
        idHandler.openID(Math.toIntExact(storage.getElement(0).getId()));
        storage.delElement(0);
        return new Pair<>(0, "Первый элемент успешно удален!\n");
    }
}
