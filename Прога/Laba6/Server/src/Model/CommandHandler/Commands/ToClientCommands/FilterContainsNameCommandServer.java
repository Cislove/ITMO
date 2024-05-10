package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.ResponseLogic.Response;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс реализации команды "filter_contains_name"
 * @author Ильнар Рахимов
 */
public class FilterContainsNameCommandServer implements ServerArgumentCommand {
    private final IStorage storage;
    public FilterContainsNameCommandServer(IStorage storage){
        this.storage = storage;
    }
    @Override
    public Pair<Integer, Response> execute(List<Object> arguments){
        List<StudyGroup> response = new LinkedList<>();
        for(StudyGroup el: storage.getAllElements()){
            if(el.getName().contains((String) arguments.get(0))){
                response.add(el);
            }
        }
        return new Pair<>(0, new Response(response));
    }

    @Override
    public void update() {

    }
}
