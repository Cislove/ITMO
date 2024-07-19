package Model.Storage;

import Model.Storage.ObjectDescription.baseMetaData;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.StudyGroupWithUser;

import java.util.LinkedList;

public class StorageWithDataBase extends Storage{
    public StorageWithDataBase(){
        super();
    }
    public int addElement(StudyGroupWithUser el) {
        if(collection.add(el)){
            return 0;
        }
        return 1;
    }
    @Override
    public int delElement(int id){
        if(collection.remove(id) != null) {
            return 0;
        }
        return 1;
    }
    public int updElement(int id, StudyGroupWithUser el){
        if(collection.set(id, el) != null) {
            return 0;
        }
        return 1;
    }
//    public StudyGroupWithUser getElement(int id){
//        for (StudyGroupWithUser el : collection) {
//            if (el.getId() == id) {
//                return el;
//            }
//        }
//        return null;
//    }
}
