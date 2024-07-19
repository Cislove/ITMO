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
 * Класс реализации команды "add"
 * @author Ильнар Рахимов
 */
public class AddCommandServer implements ServerArgumentCommand {
    private final DataManager dataManager;
    private final ClosedFieldsHolder closedFieldsHolder;
    public AddCommandServer(DataManager dataManager){
        this.dataManager = dataManager;
        closedFieldsHolder = new ClosedFieldsHolder();
    }

    @Override
    public void update() {

    }

    @Override
    public Pair<Integer, Response> execute(User user, List<Object> arguments){
        //System.out.println("Принял\n");
        StudyGroup el = (StudyGroup) arguments.get(0);
        //System.out.println(el.getClass());
        closedFieldsHolder.setFields(el);
        try {
            dataManager.addGroup(el, user);
            return new Pair<>(0, new Response(0));
        }
        catch (Exception e){
            e.printStackTrace();
            return new Pair<>(0, new Response(1));
        }
    }
}
