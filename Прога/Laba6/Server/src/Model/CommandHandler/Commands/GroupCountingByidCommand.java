package Model.CommandHandler.Commands;

import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;

/**
 * Класс реализации команды "group_counting_by_id"
 * @author Ильнар Рахимов
 */
public class GroupCountingByidCommand implements Command{
    private final IStorage storage;
    public GroupCountingByidCommand(IStorage storage){
        this.storage = storage;
    }
    @Override
    public Pair<Integer, String> execute(){
        StringBuilder response = new StringBuilder();
        for(StudyGroup el: storage.getAllElements()){
            response.append("ID = ").append(el.getId()).append(":").append(" 1\n");
        }
        return new Pair<>(0, response.toString());
    }
}
