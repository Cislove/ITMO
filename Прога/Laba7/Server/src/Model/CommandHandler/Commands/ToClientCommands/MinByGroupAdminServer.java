package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.ResponseLogic.Response;
import Model.Storage.StorageObject.StudyGroupWithUser;
import Model.Storage.StorageObject.User;

/**
 * Класс реализации команды "min_by_group_admin"
 * @author Ильнар Рахимов
 */
public class MinByGroupAdminServer implements ServerCommand {
    private final DataManager dataManager;
    public MinByGroupAdminServer(DataManager dataManager){
        this.dataManager = dataManager;
    }
    @Override
    public Pair<Integer, Response> execute(User user){
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
        //System.out.println(inst);
        if(inst == null){
            return new Pair<>(0, null);
        }
        return new Pair<>(0, new Response(inst));
    }
}
