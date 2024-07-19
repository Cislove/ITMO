package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.ResponseLogic.Response;
import Model.Storage.StorageObject.User;

/**
 * Класс реализации команды "info"
 * @author Ильнар Рахимов
 */
public class InfoServerCommand implements ServerCommand {
    DataManager dataManager;
    public InfoServerCommand(DataManager dataManager){
        this.dataManager = dataManager;
    }
    public Pair<Integer, Response> execute(User user){
        return new Pair<>(0, new Response(dataManager.getmData()));
    }
}
