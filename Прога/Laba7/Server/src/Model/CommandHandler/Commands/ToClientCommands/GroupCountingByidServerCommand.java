package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.ResponseLogic.Response;
import Model.Storage.StorageObject.User;

/**
 * Класс реализации команды "group_counting_by_id"
 * @author Ильнар Рахимов
 */
public class GroupCountingByidServerCommand implements ServerCommand {
    private final DataManager dataManager;
    public GroupCountingByidServerCommand(DataManager dataManager){
        this.dataManager = dataManager;
    }
    @Override
    public Pair<Integer, Response> execute(User user){
        return new Pair<>(0, new Response(dataManager.getCollection()));
    }
}
