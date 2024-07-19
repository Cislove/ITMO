package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.Storage.Storage;
import Model.Storage.StorageException;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.StudyGroupWithUser;
import Model.Storage.StorageObject.User;
import Model.Validation.IDHandler;
import Model.ResponseLogic.Response;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Класс реализации команды "clear"
 * @author Ильнар Рахимов
 */
public class ClearServerCommand implements ServerCommand {
    DataManager dataManager;
    public ClearServerCommand(DataManager dataManager){
        this.dataManager = dataManager;
    }
    @Override
    public Pair<Integer, Response> execute(User user) {
        LinkedList<StudyGroupWithUser> coll = dataManager.getCollection();
        for(int i = 0; i < coll.size(); i++){
            try {
                dataManager.rmGroup(coll.get(i).group.getId().intValue(), user);
                i--;
            }
            catch (SQLException e){
                e.printStackTrace();
                return new Pair<>(0, new Response(0));
            }
            catch (StorageException e){
                e.printStackTrace();
            }
        }
        //return new Pair<>(0, "Все ваши элементы удалены\n");
        return new Pair<>(0, new Response(1));
    }
}
