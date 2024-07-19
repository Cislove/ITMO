package Model.Storage;

import Model.Storage.ObjectDescription.baseMetaData;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.StudyGroupWithUser;

import java.util.LinkedList;





/**
 * Класс описывающий хранилище данных о группах {@link StudyGroup}
 * @author Ильнар Рахимов
 */
public class Storage implements IStorage {
    protected LinkedList<StudyGroupWithUser> collection;
    protected baseMetaData mDATA;
    public Storage(){
        collection = new LinkedList<>();
        mDATA = new baseMetaData("LinkedList");
    }
    public int addElement(StudyGroupWithUser el){
        collection.add(el);
        return 0;
    }
    public int delElement(int id){
        collection.remove(id);
        return 0;
    }
    public int updElement(int id, StudyGroupWithUser el){
        collection.set(id, el);
        return 0;
    }
    public StudyGroupWithUser getElement(int id){
        return collection.get(id);
    }
    public int getIdElementById (int id){
        for(int i = 0; i < collection.size(); i++){
            if(collection.get(i).group.getId() == id){
                return i;
            }
        }
        return -1;
    }

    public StudyGroupWithUser getElementById(int id){
        for (StudyGroupWithUser el : collection){
            if(el.group.getId() == id)
                return el;
        }
        return null;
    }
    public LinkedList <StudyGroupWithUser> getAllElements(){
        return collection;
    }
    public int clear(){
        collection.clear();
        //passportIds.clearPassportIds();
        return 0;
    }
    public baseMetaData getmData(){
        mDATA.updMetaData(collection);
        return mDATA;
    }
    public void setmData(baseMetaData mDATA){
        this.mDATA = mDATA;
    }
    public void setCollection(LinkedList<StudyGroupWithUser> list){
        collection = list;
    }
    /**
     * Проверка passportID на занятость
     * @return true - занят, false - свободен
     */
}
