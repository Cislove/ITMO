package Model.CommandHandler.Commands;

import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Validation.IDHandler;

/**
 * Класс реализации команды "remove_by_id"
 * @author Ильнар Рахимов
 */
public class RemoveByIdCommand implements ArgumentCommand{
    private final IStorage storage;
    private final IDHandler idHandler;
    private Integer id;
    public RemoveByIdCommand(IStorage storage, IDHandler idHandler){
        this.storage = storage;
        this.idHandler = idHandler;
        id = -1;
    }

    @Override
    public void update() {

    }

    @Override
    public Pair<Integer, String> execute(String arguments){
        Pair<Integer, String> response;
        //System.out.println(arguments);
        try {
            id = Integer.parseInt(arguments);
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
            response = new Pair<>(0, "Элемент успешно удален\n");
        }
        catch (NumberFormatException e){
            response = new Pair<>(0, "ID должен принадлежать одному из элементов коллекции!\n");
        }
        return response;
    }
}
