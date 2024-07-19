package Model.CommandHandler.Commands.OnServerCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.User;

/**
 * Класс реализации команды "head"
 * @author Ильнар Рахимов
 */
public class HeadCommand implements Command{
    DataManager dataManager;
    public HeadCommand(DataManager dataManager){
        this.dataManager = dataManager;
    }

    @Override
    public Pair<Integer, String> execute(User user){
        String response;
        if(dataManager.getCollection().isEmpty()){
            response = "В коллекции нет элементов(";
        }
        else{
            response = dataManager.getCollection().get(0).toString();
        }
        response += "\n";
        return new Pair<>(0, response);
    }
}
