package Model.Storage;
import Model.Storage.ObjectDescription.baseMetaData;
import Model.Storage.StorageObject.StudyGroup;

import java.util.LinkedList;





/**
 * Класс описывающий хранилище данных о группах {@link StudyGroup}
 * @author Ильнар Рахимов
 */
public class Storage implements IStorage {
    protected LinkedList<StudyGroup> collection;
    protected baseMetaData mDATA;
    protected PassportIdStorage passportIds;
    public Storage(){
        collection = new LinkedList<>();
        mDATA = new baseMetaData("LinkedList");
        passportIds = new PassportIdStorage();
    }
    @Override
    public int addElement(StudyGroup el){
        collection.add(el);
        if(el.getGroupAdmin() != null) {
            //PassportIdStorage.put(el.getGroupAdmin().getPassportID(), true);
            passportIds.addPassportId(el.getGroupAdmin().getPassportID());
        }
        return 0;
    }
    public int delElement(int id){
        if(collection.get(id).getGroupAdmin() != null) {
            passportIds.removePassportId(collection.get(id).getGroupAdmin().getPassportID());
        }
        collection.remove(id);
        return 0;
    }
    public int updElement(int id, StudyGroup el){
        collection.set(id, el);
        if(el.getGroupAdmin() != null) {
            passportIds.updatePassportId(el.getGroupAdmin().getPassportID(), true);
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
    public void setCollection(LinkedList<StudyGroup> list){
        collection = list;
    }
    /**
     * Проверка passportID на занятость
     * @return true - занят, false - свободен
     */
    public boolean checkPassportId(String passportId){
        return passportIds.checkPassportId(passportId);
    }
}
