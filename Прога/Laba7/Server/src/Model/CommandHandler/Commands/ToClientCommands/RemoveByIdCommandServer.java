package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.Storage.StorageException;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.User;
import Model.Validation.IDHandler;
import Model.ResponseLogic.Response;

import java.sql.SQLException;
import java.util.List;

/**
 * Класс реализации команды "remove_by_id"
 * @author Ильнар Рахимов
 */
public class RemoveByIdCommandServer implements ServerArgumentCommand {
    private final DataManager dataManager;
    public RemoveByIdCommandServer(DataManager dataManager){
        this.dataManager = dataManager;
    }

    @Override
    public void update() {

    }

    @Override
    public Pair<Integer, Response> execute(User user, List<Object> arguments){
        //System.out.println(arguments);
        Pair<Integer, Response> response;
        int id = (int) arguments.get(0);
        try {
            dataManager.rmGroup(id, user);
        }
        catch (StorageException e){
            return new Pair<>(0, new Response(1));
        }
        catch (SQLException e){
            return new Pair<>(0, new Response(2));
        }
        return new Pair<>(0, new Response(0));
    }
}
