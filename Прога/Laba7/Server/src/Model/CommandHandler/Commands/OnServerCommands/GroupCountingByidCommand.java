package Model.CommandHandler.Commands.OnServerCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.StudyGroupWithUser;
import Model.Storage.StorageObject.User;
import org.w3c.dom.CDATASection;

/**
 * Класс реализации команды "group_counting_by_id"
 * @author Ильнар Рахимов
 */
public class GroupCountingByidCommand implements Command{
    private final DataManager dataManager;
    public GroupCountingByidCommand(DataManager dataManager){
        this.dataManager = dataManager;
    }
    @Override
    public Pair<Integer, String> execute(User user){
        StringBuilder response = new StringBuilder();
        for(StudyGroupWithUser el: dataManager.getCollection()){
            response.append("ID = ").append(el.group.getId()).append(":").append(" 1\n");
        }
        return new Pair<>(0, response.toString());
    }
}
