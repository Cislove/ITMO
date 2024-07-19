package Model.CommandHandler.Commands.OnServerCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.Storage.StorageException;
import Model.Storage.StorageObject.StudyGroupWithUser;
import Model.Storage.StorageObject.User;
import Model.Validation.IDHandler;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Класс реализации команды "remove_first"
 * @author Ильнар Рахимов
 */
public class removeFirstCommand implements Command{
    DataManager dataManager;
    public removeFirstCommand(DataManager dataManager){
        this.dataManager = dataManager;
    }
    @Override
    public Pair<Integer, String> execute(User user){
        LinkedList<StudyGroupWithUser> coll = dataManager.getCollection();
        for(int i = 0; i < coll.size(); i++){
            try{
                dataManager.rmGroup(i, user);
                break;
            }
            catch(StorageException ignored){}
            catch(SQLException e){
                return new Pair<>(0, "Ошибка удаления!\n");
            }
        }
        return new Pair<>(0, "Первый элемент успешно удален!\n");
    }
}
