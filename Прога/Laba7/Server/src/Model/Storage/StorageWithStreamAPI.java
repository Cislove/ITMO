package Model.Storage;

import Model.Storage.ObjectDescription.baseMetaData;
import Model.Storage.StorageObject.StudyGroup;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static Logger.MyLogger.logger;

public class StorageWithStreamAPI extends Storage implements IStorage{

    @Override
    public int addElement(StudyGroup el) {
        List <StudyGroup> promt = super.collection.stream().
                filter(x -> x.getGroupAdmin() == null ||
                        el.getGroupAdmin() == null ||
                        !Objects.equals(x.getGroupAdmin().getPassportID(), el.getGroupAdmin().getPassportID())).toList();
        if(promt.size() == super.collection.size()){
            super.collection.add(el);
            logger.info("Добавление элемента в коллекцию: ");
            return 0;
        }
        logger.info("Добавляемый элемент не соответствует формату: ");
        return 1;
    }

    @Override
    public int delElement(int id) {
        if(super.collection.remove(id) != null){
            logger.info("Удаление элемента из коллекции: " + collection.get(id));
            return 0;
        }
        return -1;
    }

    @Override
    public int updElement(int id, StudyGroup el) {
        List <StudyGroup> promt = super.collection.stream().
                filter(x -> x.getGroupAdmin() == null ||
                        el.getGroupAdmin() == null ||
                        !Objects.equals(x.getGroupAdmin().getPassportID(), el.getGroupAdmin().getPassportID())).toList();
        if(promt.size() >= (super.collection.size() - 1)){
            logger.info("Обновление элемента коллекции\n Старый: " + collection.get(id) + "Новый: " + el);
            super.collection.set(id, el);
            return 0;
        }
        return 1;
    }

    @Override
    public StudyGroup getElement(int id) {
        try{
            return super.collection.get(id);
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public LinkedList<StudyGroup> getAllElements() {
        if(super.collection.get(0).getCoordinates().getXCord() == null){
            System.out.println("null");
        }
        return super.getAllElements().stream().sorted((o1, o2) -> (int)(o1.getId() - o2.getId())).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public int clear() {
        logger.info("Очистка коллекции");
        super.collection.clear();
        return 0;
    }

    @Override
    public baseMetaData getmData() {
        return super.getmData();
    }

    @Override
    public void setmData(baseMetaData mDATA) {
        logger.info("Смена метаданных\n" +
                "Старые:" +
                "Информация о коллекции:" + "\n" +
                "Дата инициализации - " + mDATA.initDate + "\n" +
                "Тип коллекции - " + mDATA.typeCollection + "\n" +
                "Размер коллекции - " + mDATA.size + "\n" +
                "Новые:" +
                "Информация о коллекции:" + "\n" +
                "Дата инициализации - " + mDATA.initDate + "\n" +
                "Тип коллекции - " + mDATA.typeCollection + "\n" +
                "Размер коллекции - " + mDATA.size + "\n");
        super.setmData(mDATA);
    }

    @Override
    public void setCollection(LinkedList<StudyGroup> list) {
        logger.info("Инициализация новой коллекции");
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
