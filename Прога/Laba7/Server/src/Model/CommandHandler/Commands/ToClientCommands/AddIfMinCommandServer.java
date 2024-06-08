package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.CommandHandler.Holders.ClosedFieldsHolder;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Validation.IDHandler;
import Model.ResponseLogic.Response;

import java.util.List;

/**
 * Класс реализации команды "add_if_min"
 * @author Ильнар Рахимов
 */
public class AddIfMinCommandServer implements ServerArgumentCommand {
    private final IStorage storage;
    private final ClosedFieldsHolder closedFieldsHolder;
    public AddIfMinCommandServer(IStorage storage, IDHandler idHandler){
        this.storage = storage;
        closedFieldsHolder = new ClosedFieldsHolder(idHandler);
    }
    @Override
    public Pair<Integer, Response> execute(List<Object> arguments){
        StudyGroup el = (StudyGroup) arguments.get(0);
        boolean flag = true;
        for(StudyGroup inst: storage.getAllElements()){
            if(inst.compareTo(el) <= 0) {
                flag = false;
                break;
            }
        }
        if(flag){
            closedFieldsHolder.setFields(el);
            return new Pair<>(0, new Response(storage.addElement(el)));
        }
        return new Pair<>(0, new Response(2));
    }

    @Override
    public void update() {

    }
}
