package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.IStorage;
import Model.ResponseLogic.Response;


/**
 * Класс реализации команды "show"
 * @author Ильнар Рахимов
 */
public class ShowServerCommand implements ServerCommand {
    IStorage storage;
    public ShowServerCommand(IStorage storage){
        this.storage = storage;
    }
    @Override
    public Pair<Integer, Response> execute() {
        return new Pair<>(0, new Response(storage.getAllElements()));
    }
}
