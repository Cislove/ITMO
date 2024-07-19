package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.ResponseLogic.Response;
import Model.Storage.StorageObject.User;


/**
 * Класс реализации команды "show"
 * @author Ильнар Рахимов
 */
public class ShowServerCommand implements ServerCommand {
    DataManager dataManager;
    public ShowServerCommand(DataManager dataManager){
        this.dataManager = dataManager;
    }
    @Override
    public Pair<Integer, Response> execute(User user) {
        return new Pair<>(0, new Response(dataManager.getCollection()));
    }
}
