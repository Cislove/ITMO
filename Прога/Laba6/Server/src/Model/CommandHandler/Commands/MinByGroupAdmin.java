package Model.CommandHandler.Commands;

import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;

/**
 * Класс реализации команды "min_by_group_admin"
 * @author Ильнар Рахимов
 */
public class MinByGroupAdmin implements Command{
    private final IStorage storage;
    public MinByGroupAdmin(IStorage storage){
        this.storage = storage;
    }
    @Override
    public Pair<Integer, String> execute(){
        StudyGroup inst = null;
        for(StudyGroup el: storage.getAllElements()){
            if(el.getGroupAdmin() != null){
                if(inst == null){
                    inst = el;
                    continue;
                }
                if (inst.getGroupAdmin().compareTo(el.getGroupAdmin()) > 0){
                    inst = el;
                }
            }
        }
        if(inst == null){
            return new Pair<>(0, "Все элементы коллекции без поля groupAdmin\n");
        }
        return new Pair<>(0, inst.toString());
    }
}
