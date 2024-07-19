package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.ResponseLogic.Response;
import Model.Storage.StorageObject.StudyGroupWithUser;
import Model.Storage.StorageObject.User;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс реализации команды "filter_contains_name"
 * @author Ильнар Рахимов
 */
public class FilterContainsNameCommandServer implements ServerArgumentCommand {
    private final DataManager dataManager;
    public FilterContainsNameCommandServer(DataManager dataManager){
        this.dataManager = dataManager;
    }
    @Override
    public Pair<Integer, Response> execute(User user, List<Object> arguments){
        List<StudyGroupWithUser> response = new LinkedList<>();
        for(StudyGroupWithUser el: dataManager.getCollection()){
            if(el.group.getName().contains((String) arguments.get(0))){
                response.add(el);
            }
        }
        return new Pair<>(0, new Response(response));
    }

    @Override
    public void update() {

    }
}
