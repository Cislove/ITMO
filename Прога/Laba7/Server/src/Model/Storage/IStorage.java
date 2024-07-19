package Model.Storage;

import Model.Storage.ObjectDescription.baseMetaData;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.StudyGroupWithUser;

import java.util.LinkedList;

/**
 * Интерфейс взаимодействия с хранилищем {@link Storage}
 * @author Ильнар Рахимов
 */
public interface IStorage {
    int addElement(StudyGroupWithUser el);
    int delElement(int id);
    int updElement(int id, StudyGroupWithUser el);
    StudyGroupWithUser getElement(int id);
    LinkedList<StudyGroupWithUser> getAllElements();
    int clear();
    baseMetaData getmData();
    void setmData(baseMetaData mData);
    void setCollection(LinkedList<StudyGroupWithUser> collection);
}
