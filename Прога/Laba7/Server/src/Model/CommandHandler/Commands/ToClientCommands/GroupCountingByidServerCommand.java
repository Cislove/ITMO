package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.IStorage;
import Model.ResponseLogic.Response;

/**
 * Класс реализации команды "group_counting_by_id"
 * @author Ильнар Рахимов
 */
public class GroupCountingByidServerCommand implements ServerCommand {
    private final IStorage storage;
    public GroupCountingByidServerCommand(IStorage storage){
        this.storage = storage;
    }
    @Override
    public Pair<Integer, Response> execute(){
        return new Pair<>(0, new Response(storage.getAllElements()));
    }
}
