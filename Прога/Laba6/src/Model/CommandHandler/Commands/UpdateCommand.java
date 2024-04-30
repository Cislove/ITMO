package Model.CommandHandler.Commands;

import Model.CommandHandler.Holders.FieldHolder;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Validation.IDHandler;
import Model.Validation.Parser;
import Model.Validation.Validator;

import java.util.LinkedList;

/**
 * Класс реализации команды "update"
 * @author Ильнар Рахимов
 */
public class UpdateCommand implements ArgumentCommand{
    private final IStorage storage;
    private FieldHolder fieldHolder;
    private final IDHandler idHandler;
    private Integer id;

    public UpdateCommand(IStorage storage, IDHandler idHandler){
        this.storage = storage;
        fieldHolder = new FieldHolder(new Validator(storage), new Parser());
        this.idHandler = idHandler;
        id = -1;
    }
    @Override
    public Pair<Integer, String> execute(String arguments){
        Pair<Integer, String> response;
        if(id == -1){
            try {
                //System.out.println(arguments);
                id = Integer.parseInt(arguments);
                if (id < 1 || !idHandler.checkId(id)) {
                    id = -1;
                    throw new NumberFormatException();
                }
                response = fieldHolder.execute(null);
            } catch (NumberFormatException e) {
                response = new Pair<>(0, "ID должен принадлежать одному из элементов коллекции!\n");
            }
            return response;
        }
        response = fieldHolder.execute(arguments);
        if (response.getLeft() == 0) {
            StudyGroup el = fieldHolder.getReadyEl();
            el.setId(Long.valueOf(id));
            LinkedList<StudyGroup> collection = storage.getAllElements();
            for(int i = 0; i < collection.size(); i++){
                if(collection.get(i).getId().equals(Long.valueOf(id))){
                    el.setCreationDate(collection.get(i).getCreationDate());
                    storage.updElement(i, el);
                }
            }
            response.setRight("Элемент успешно обновлен\n");
            id = -1;
            return response;
        }
        return response;
    }
    @Override
    public void update() {
        fieldHolder = new FieldHolder(new Validator(storage), new Parser());
    }
}
