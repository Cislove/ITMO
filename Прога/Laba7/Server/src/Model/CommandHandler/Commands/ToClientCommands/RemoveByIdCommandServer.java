package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Validation.IDHandler;
import Model.ResponseLogic.Response;

import java.util.List;

/**
 * Класс реализации команды "remove_by_id"
 * @author Ильнар Рахимов
 */
public class RemoveByIdCommandServer implements ServerArgumentCommand {
    private final IStorage storage;
    private final IDHandler idHandler;
    private Integer id;
    public RemoveByIdCommandServer(IStorage storage, IDHandler idHandler){
        this.storage = storage;
        this.idHandler = idHandler;
        id = -1;
    }

    @Override
    public void update() {

    }

    @Override
    public Pair<Integer, Response> execute(List<Object> arguments){
        //System.out.println(arguments);
        try {
            id = (Integer) arguments.get(0);
            if (id < 1 || !idHandler.checkId(id)) {
                id = -1;
                throw new NumberFormatException();
            }
            int i = 0;
            for(StudyGroup el: storage.getAllElements()){
                if(el.getId().equals(Long.valueOf(id))){
                    storage.delElement(i);
                    idHandler.openID(id);
                    //System.out.println(i);
                }
                i++;
            }
            return new Pair<>(0, new Response(0));
        }
        catch (NumberFormatException e){
            return new Pair<>(0, new Response(1));
        }
    }
}
