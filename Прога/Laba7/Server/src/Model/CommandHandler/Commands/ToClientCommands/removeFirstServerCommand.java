package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.Storage.StorageException;
import Model.Storage.StorageObject.StudyGroupWithUser;
import Model.Storage.StorageObject.User;
import Model.Validation.IDHandler;
import Model.ResponseLogic.Response;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Класс реализации команды "remove_first"
 * @author Ильнар Рахимов
 */
public class removeFirstServerCommand implements ServerCommand {
    DataManager dataManager;
    IDHandler idHandler;
    public removeFirstServerCommand(DataManager dataManager){
        this.dataManager = dataManager;
    }
    @Override
    public Pair<Integer, Response> execute(User user){
        LinkedList<StudyGroupWithUser> coll = dataManager.getCollection();
        if(coll.isEmpty()){
            return new Pair<>(0, new Response(1));
        }
        //System.out.println("remove_first 123");
        boolean flag = false;
        for(int i = 0; i < coll.size(); i++){
            try{
                dataManager.rmGroup(coll.get(i).group.getId().intValue() , user);
                flag = true;
                break;
            }
            catch(StorageException ignored){
                ignored.printStackTrace();
            }
            catch(SQLException ignored){
                ignored.printStackTrace();
                //return new Pair<>(0, new Response(0));
            }
        }
        if(flag) return new Pair<>(0, new Response(0));
        //return new Pair<>(0, "Первый элемент успешно удален!\n");
        return new Pair<>(0, new Response(1));
    }
}
