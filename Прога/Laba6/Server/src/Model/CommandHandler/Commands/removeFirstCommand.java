package Model.CommandHandler.Commands;

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
        idHandler.openID(Math.toIntExact(storage.getElement(0).getId()));
        storage.delElement(0);
        return new Pair<>(0, "Первый элемент успешно удален!\n");
    }
}
