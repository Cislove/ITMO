package Model.CommandHandler.Commands;

import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;


/**
 * Класс реализации команды "show"
 * @author Ильнар Рахимов
 */
public class ShowCommand implements Command{
    IStorage storage;
    public ShowCommand(IStorage storage){
        this.storage = storage;
    }
    @Override
    public Pair<Integer, String> execute() {
        StringBuilder response = new StringBuilder();
        if(storage.getAllElements().isEmpty()){
            response.append("В коллекции отсутствуют элементы\n");
        }
        for(StudyGroup coll: storage.getAllElements()){
            response.append(coll.toString());
        }
        return new Pair<>(0, response.toString());
    }
}
