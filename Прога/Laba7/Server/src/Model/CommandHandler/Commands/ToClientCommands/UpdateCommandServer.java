package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.CommandHandler.Holders.FieldHolder;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.Storage.StorageException;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.StudyGroupWithUser;
import Model.Storage.StorageObject.User;
import Model.Validation.IDHandler;
import Model.Validation.Parser;
import Model.Validation.Validator;
import Model.ResponseLogic.Response;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс реализации команды "update"
 * @author Ильнар Рахимов
 */
public class UpdateCommandServer implements ServerArgumentCommand {
    private final DataManager dataManager;
    //private Integer id;

    public UpdateCommandServer(DataManager dataManager){
        this.dataManager = dataManager;
        //id = -1;
    }
    @Override
    public Pair<Integer, Response> execute(User user, List<Object> arguments){
        Pair<Integer, Response> response;
        StudyGroup el = (StudyGroup) arguments.get(0);
        el.setCreationDate();
        //System.out.println(el);
        try {
            dataManager.updGroup(el.getId().intValue(), el, user);
        }
        catch (StorageException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return new Pair<>(0, new Response(2));
        }
        catch (SQLException e){
            if(e.getMessage() == "1"){
                return new Pair<>(0, new Response(3));
            }
            e.printStackTrace();
            return new Pair<>(0, new Response(1));
        }
        return new Pair<>(0, new Response(0));
    }
    @Override
    public void update() {
    }
}
