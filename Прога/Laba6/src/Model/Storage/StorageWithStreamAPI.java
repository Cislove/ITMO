package Model.Storage;

import Model.Storage.ObjectDescription.baseMetaData;
import Model.Storage.StorageObject.StudyGroup;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class StorageWithStreamAPI extends Storage implements IStorage{

    @Override
    public int addElement(StudyGroup el) {
        List <StudyGroup> promt = super.collection.stream().
                filter(x -> x.getGroupAdmin() == null ||
                        !Objects.equals(x.getGroupAdmin().getPassportID(), el.getGroupAdmin().getPassportID())).toList();
        if(promt.size() == super.collection.size()){
            super.collection.add(el);
            return 0;
        }
        return -1;
    }

    @Override
    public int delElement(int id) {
        if(super.collection.remove(id) != null){
            return 0;
        }
        return -1;
    }

    @Override
    public int updElement(int id, StudyGroup el) {
        List <StudyGroup> promt = super.collection.stream().
                filter(x -> x.getGroupAdmin() == null ||
                        !Objects.equals(x.getGroupAdmin().getPassportID(), el.getGroupAdmin().getPassportID())).toList();
        if(promt.size() == (super.collection.size() - 1)){
            super.collection.set(id, el);
            return 0;
        }
        return -1;
    }

    @Override
    public StudyGroup getElement(int id) {
        return super.collection.get(id);
    }

    @Override
    public LinkedList<StudyGroup> getAllElements() {
        List<StudyGroup> out = super.getAllElements().stream().sorted((o1, o2) -> (int)(o1.getId() - o2.getId())).toList();
        return (LinkedList<StudyGroup>) out;
    }

    @Override
    public int clear() {
        super.collection.clear();
        return 0;
    }

    @Override
    public baseMetaData getmData() {
        return super.getmData();
    }

    @Override
    public void setmData(baseMetaData mDATA) {
        super.setmData(mDATA);
    }

    @Override
    public void setCollection(LinkedList<StudyGroup> list) {
        super.setCollection(list);
    }

    @Override
    public boolean checkPassportId(String passportId) {
        List <StudyGroup> promt = super.collection.stream().
                dropWhile(x -> x.getGroupAdmin() == null ||
                        !Objects.equals(x.getGroupAdmin().getPassportID(), passportId)).toList();
        if(promt.isEmpty()){
            return false;
        }
        return true;
    }
}
