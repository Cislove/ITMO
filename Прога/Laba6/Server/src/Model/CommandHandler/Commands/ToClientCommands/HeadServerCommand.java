package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.IStorage;
import Model.ResponseLogic.Response;

/**
 * Класс реализации команды "head"
 * @author Ильнар Рахимов
 */
public class HeadServerCommand implements ServerCommand {
    IStorage storage;
    public HeadServerCommand(IStorage storage){
        this.storage = storage;
    }

    @Override
    public Pair<Integer, Response> execute(){
        return new Pair<>(0, new Response(storage.getElement(0)));
    }
}
