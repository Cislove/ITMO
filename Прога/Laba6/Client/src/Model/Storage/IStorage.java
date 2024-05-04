package Model.Storage;

import Client.Model.Storage.ObjectDescription.baseMetaData;
import Client.Model.Storage.StorageObject.StudyGroup;

import java.util.LinkedList;

/**
 * Интерфейс взаимодействия с хранилищем {@link Storage}
 * @author Ильнар Рахимов
 */
public interface IStorage {
    int addElement(StudyGroup el);
    int delElement(int id);
    int updElement(int id, StudyGroup el);
    StudyGroup getElement(int id);
    LinkedList<StudyGroup> getAllElements();
    int clear();
    baseMetaData getmData();
    void setmData(baseMetaData mData);
    void setCollection(LinkedList<StudyGroup> collection);
    boolean checkPassportId(String passportId);
}
