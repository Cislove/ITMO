package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Holders.ClosedFieldsHolder;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Validation.IDHandler;
import View.ResponseLogic.Response;

import java.util.List;

/**
 * Класс реализации команды "add_if_min"
 * @author Ильнар Рахимов
 */
public class AddIfMinCommand implements ArgumentCommand {
    private final IStorage storage;
    private final ClosedFieldsHolder closedFieldsHolder;
    public AddIfMinCommand(IStorage storage, IDHandler idHandler){
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
            storage.addElement(el);
            return new Pair<>(0, new Response("Элемент успешно добавлен\n"));
        }
        return new Pair<>(0, new Response("В коллекции есть меньший элемент\n"));
    }

    @Override
    public void update() {

    }
}
