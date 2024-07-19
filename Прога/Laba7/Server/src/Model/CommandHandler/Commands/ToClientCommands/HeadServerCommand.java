package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.ResponseLogic.Response;
import Model.Storage.StorageObject.User;

/**
 * Класс реализации команды "head"
 * @author Ильнар Рахимов
 */
public class HeadServerCommand implements ServerCommand {
    DataManager dataManager;
    public HeadServerCommand(DataManager dataManager){
        this.dataManager = dataManager;
    }

    @Override
    public Pair<Integer, Response> execute(User user){
        if(dataManager.getCollection().isEmpty()){
            return new Pair<>(0, new Response(null));
        }
        else{
            return new Pair <>(0, new Response(dataManager.getCollection().get(0)));
        }
    }
}
