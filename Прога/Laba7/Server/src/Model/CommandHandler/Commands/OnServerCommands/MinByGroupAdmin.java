package Model.CommandHandler.Commands.OnServerCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.StudyGroupWithUser;
import Model.Storage.StorageObject.User;

/**
 * Класс реализации команды "min_by_group_admin"
 * @author Ильнар Рахимов
 */
public class MinByGroupAdmin implements Command{
    private final DataManager dataManager;
    public MinByGroupAdmin(DataManager dataManager){
        this.dataManager = dataManager;
    }
    @Override
    public Pair<Integer, String> execute(User user){
        StudyGroupWithUser inst = null;
        for(StudyGroupWithUser el: dataManager.getCollection()){
            if(el.group.getGroupAdmin() != null){
                if(inst == null){
                    inst = el;
                    continue;
                }
                if (inst.group.getGroupAdmin().compareTo(el.group.getGroupAdmin()) > 0){
                    inst = el;
                }
            }
        }
        if(inst == null){
            return new Pair<>(0, "Все элементы коллекции без поля groupAdmin\n");
        }
        return new Pair<>(0, inst.toString());
    }
}
