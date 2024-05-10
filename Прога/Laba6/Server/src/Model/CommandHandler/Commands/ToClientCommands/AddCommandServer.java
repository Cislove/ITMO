package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.CommandHandler.Holders.ClosedFieldsHolder;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Validation.IDHandler;
import Model.ResponseLogic.Response;

import java.util.List;

/**
 * Класс реализации команды "add"
 * @author Ильнар Рахимов
 */
public class AddCommandServer implements ServerArgumentCommand {
    private final IStorage storage;
    private final ClosedFieldsHolder closedFieldsHolder;
    public AddCommandServer(IStorage storage, IDHandler idHandler){
        this.storage = storage;
        closedFieldsHolder = new ClosedFieldsHolder(idHandler);
    }

    @Override
    public void update() {

    }

    @Override
    public Pair<Integer, Response> execute(List<Object> arguments){
        StudyGroup el = (StudyGroup) arguments.get(0);
        closedFieldsHolder.setFields(el);
        storage.addElement(el);
        return new Pair<>(0, new Response(storage.addElement(el)));
    }
}
