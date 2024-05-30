package Model.CommandHandler.Commands.ToClientCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.ResponseLogic.Response;

/**
 * Класс реализации команды "min_by_group_admin"
 * @author Ильнар Рахимов
 */
public class MinByGroupAdminServer implements ServerCommand {
    private final IStorage storage;
    public MinByGroupAdminServer(IStorage storage){
        this.storage = storage;
    }
    @Override
    public Pair<Integer, Response> execute(){
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
        return new Pair<>(0, new Response(inst));
    }
}
