package Model.CommandHandler.Commands.OnServerCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.StudyGroupWithUser;
import Model.Storage.StorageObject.User;


/**
 * Класс реализации команды "show"
 * @author Ильнар Рахимов
 */
public class ShowCommand implements Command{
    DataManager dataManager;
    public ShowCommand(DataManager dataManager){
        this.dataManager = dataManager;
    }
    @Override
    public Pair<Integer, String> execute(User user){
        StringBuilder response = new StringBuilder();
        if(dataManager.getCollection().isEmpty()){
            response.append("В коллекции отсутствуют элементы\n");
        }
        for(StudyGroupWithUser coll: dataManager.getCollection()){
            //System.out.println(storage.getAllElements());
            response.append(coll.toString());
        }
        return new Pair<>(0, response.toString());
    }
}
