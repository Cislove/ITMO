package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.CommandHandler.Holders.FieldHolder;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Validation.IDHandler;
import Model.Validation.Parser;
import Model.Validation.Validator;
import Model.ResponseLogic.Response;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс реализации команды "update"
 * @author Ильнар Рахимов
 */
public class UpdateCommandServer implements ServerArgumentCommand {
    private final IStorage storage;
    private FieldHolder fieldHolder;
    private final IDHandler idHandler;
    private Integer id;

    public UpdateCommandServer(IStorage storage, IDHandler idHandler){
        this.storage = storage;
        fieldHolder = new FieldHolder(new Validator(storage), new Parser());
        this.idHandler = idHandler;
        id = -1;
    }
    @Override
    public Pair<Integer, Response> execute(List<Object> arguments){
        Pair<Integer, Response> response;
        if(id == -1){
            try {
                //System.out.println(arguments);
                id = (int) arguments.get(0);
                if (id < 1 || !idHandler.checkId(id)) {
                    id = -1;
                    throw new NumberFormatException();
                }
                response = new Pair<>(1, new Response(0));
            } catch (NumberFormatException e) {
                response = new Pair<>(0, new Response(1));
            }
            return response;
        }
        StudyGroup el = (StudyGroup) arguments.get(0);
        el.setId(Long.valueOf(id));
        int out = 1;
        LinkedList<StudyGroup> collection = storage.getAllElements();
        for(int i = 0; i < collection.size(); i++){
            if(collection.get(i).getId().equals(Long.valueOf(id))){
                el.setCreationDate(collection.get(i).getCreationDate());
                out = storage.updElement(i, el);
            }
        }
        id = -1;
        return new Pair<Integer, Response>(0, new Response(out));
    }
    @Override
    public void update() {
        fieldHolder = new FieldHolder(new Validator(storage), new Parser());
    }
}
