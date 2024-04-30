package Model.Storage;
import Model.Storage.StorageObject.*;
import Model.Storage.ObjectDescription.baseMetaData;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Класс описывающий хранилище данных о группах {@link Model.Storage.StorageObject.StudyGroup}
 * @author Ильнар Рахимов
 */
public class Storage implements IStorage {
    private LinkedList<StudyGroup> collection;
    private baseMetaData mDATA;
    private HashMap<String, Boolean> passportIdStorage;
    public Storage(){
        collection = new LinkedList<>();
        mDATA = new baseMetaData("LinkedList");
        passportIdStorage = new HashMap<>();
    }
    public LinkedList<StudyGroup> getCollection() {
        return collection;
    }
    public int addElement(StudyGroup el){
        collection.add(el);
        if(el.getGroupAdmin() != null) {
            passportIdStorage.put(el.getGroupAdmin().getPassportID(), true);
        }
        return 0;
    }
    public int delElement(int id){
        if(collection.get(id).getGroupAdmin() != null) {
            passportIdStorage.replace(collection.get(id).getGroupAdmin().getPassportID(), true);
        }
        collection.remove(id);
        return 0;
    }
    public int updElement(int id, StudyGroup el){
        collection.set(id, el);
        if(el.getGroupAdmin() != null) {
            passportIdStorage.replace(el.getGroupAdmin().getPassportID(), true);
        }
        return 0;
    }
    public StudyGroup getElement(int id){
        return collection.get(id);
    }
    public LinkedList <StudyGroup> getAllElements(){
        return collection;
    }
    public int clear(){
        collection.clear();
        passportIdStorage = new HashMap<>();
        return 0;
    }
    public baseMetaData getmData(){
        mDATA.updMetaData(collection);
        return mDATA;
    }
    public void setmData(baseMetaData mDATA){
        this.mDATA = mDATA;
    }
    public void setCollection(LinkedList<StudyGroup> list){
        collection = list;
    }
    private void delPassportId(String passportId){
        passportIdStorage.remove(passportId);
    }
    private void addPassportId(String passportId){
        passportIdStorage.put(passportId, true);
    }
    /**
     * Проверка passportID на занятость
     * @return true - занят, false - свободен
     */
    public boolean checkPassportId(String passportId){
        return passportIdStorage.containsKey(passportId);
    }
}
