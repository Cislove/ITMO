package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.IStorage;
import Model.ResponseLogic.Response;

/**
 * Класс реализации команды "info"
 * @author Ильнар Рахимов
 */
public class InfoServerCommand implements ServerCommand {
    IStorage storage;
    public InfoServerCommand(IStorage storage){
        this.storage = storage;
    }
    public Pair<Integer, Response> execute(){
        return new Pair<>(0, new Response(storage.getmData()));
    }
}
