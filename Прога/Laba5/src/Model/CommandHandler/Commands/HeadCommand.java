package Model.CommandHandler.Commands;

import Model.Storage.IStorage;

/**
 * Класс реализации команды "head"
 * @author Ильнар Рахимов
 */
public class HeadCommand implements Command{
    IStorage storage;
    public HeadCommand(IStorage storage){
        this.storage = storage;
    }

    @Override
    public Pair<Integer, String> execute(){
        String response;
        if(storage.getAllElements().isEmpty()){
            response = "В коллекции нет элементов(";
        }
        else{
            response = storage.getElement(0).toString();
        }
        response += "\n";
        return new Pair<>(0, response);
    }
}
