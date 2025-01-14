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
        System.out.println(storage.getElement(0));
        if(storage.getElement(0) == null){
            return new Pair<>(0, new Response(null));
        }
        return new Pair<>(0, new Response(storage.getElement(0)));
    }
}
