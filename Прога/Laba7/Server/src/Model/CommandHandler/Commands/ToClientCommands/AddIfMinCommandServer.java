package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.CommandHandler.Holders.ClosedFieldsHolder;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.StudyGroupWithUser;
import Model.Storage.StorageObject.User;
import Model.Validation.IDHandler;
import Model.ResponseLogic.Response;

import java.util.List;

/**
 * Класс реализации команды "add_if_min"
 * @author Ильнар Рахимов
 */
public class AddIfMinCommandServer implements ServerArgumentCommand {
    private final DataManager dataManager;
    private final ClosedFieldsHolder closedFieldsHolder;
    public AddIfMinCommandServer(DataManager dataManager){
        this.dataManager = dataManager;
        closedFieldsHolder = new ClosedFieldsHolder();
    }
    @Override
    public Pair<Integer, Response> execute(User user, List<Object> arguments){
        StudyGroup el = (StudyGroup) arguments.get(0);
        boolean flag = true;
        for(StudyGroupWithUser inst: dataManager.getCollection()){
            if(inst.group.compareTo(el) <= 0) {
                flag = false;
                break;
            }
        }
        if(flag){
            closedFieldsHolder.setFields(el);
            try {
                dataManager.addGroup(el, user);
                return new Pair<>(0, new Response(0));
            }
            catch (Exception e){
                return new Pair<>(0, new Response(1));
            }
        }
        return new Pair<>(0, new Response(2));
    }

    @Override
    public void update() {

    }
}
