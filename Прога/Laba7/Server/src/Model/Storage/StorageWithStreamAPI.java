//package Model.Storage;
//
//import Model.IODriver.IOHandlerWithDatabase;
//import Model.IODriver.SQLConverter.Converter;
//import Model.IODriver.SQLParser.Parser;
//import Model.Storage.ObjectDescription.baseMetaData;
//import Model.Storage.StorageObject.StudyGroup;
//import Model.Storage.StorageObject.User;
//
//import java.sql.SQLException;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//import static Logger.MyLogger.logger;
//
//public class StorageWithStreamAPI extends Storage implements IStorage{
//    IOHandlerWithDatabase ioHandler;
//    Parser parser = new Parser();
//    Converter converter = new Converter();
//    public StorageWithStreamAPI(IOHandlerWithDatabase ioHandler) {
//        super();
//        this.ioHandler = ioHandler;
//    }
//    public int addElement(StudyGroup el, User user) {
//        try {
//            Integer person_id = null;
//            if(el.getGroupAdmin() != null){
//                String str = converter.convertPerson(el.getGroupAdmin());
//                int res = ioHandler.executeSqlRequest("INSERT INTO person VALUES " + str);
//                if(res == 0){
//                    logger.info("Добавляемый элемент не соответствует формату: ");
//                    return 1;
//                }
//                person_id = ioHandler.getSqlResponse("SELECT id FROM person WHERE passport_id = " +
//                        el.getGroupAdmin().getPassportID()).getInt("id");
//            }
//            String str = converter.convertStudyGroup(el, user.getId(), person_id);
//            ioHandler.executeSqlRequest("INSERT INTO study_group (id_user, name, id_coordinates, " +
//                    "creation_date, students_count, form_of_education, semester, group_admin) VALUES " + str);
//            int id = ioHandler.getSqlResponse("SELECT * FROM study_gruop " +
//                    "WHERE group_admin = (SELECT id FROM person WHERE passport_id = " + el.getGroupAdmin().getPassportID() +
//                    " )").getInt("id");
//            el.setId((long) id);
//            super.collection.add(el);
//            logger.info("Добавление элемента в коллекцию: ");
//            return 0;
//        }
//        catch(SQLException e){
//            return 1;
//        }
//    }
//
//    @Override
//    public int delElement(int id) {
//        if(super.collection.remove(id) != null){
//            try {
//                ioHandler.getSqlResponse("DELETE FROM study_group WHERE id = " + id + " CASCADE");
//            }
//            catch (SQLException e){
//                throw new RuntimeException(e);
//            }
//            logger.info("Удаление элемента из коллекции: " + collection.get(id));
//            return 0;
//        }
//        return -1;
//    }
//
//    @Override
//    public int updElement(int id, StudyGroup el) {
//            try {
//                Integer person_id = null;
//                if (el.getGroupAdmin() != null) {
//                    String str = converter.convertPersonUpdate(el.getGroupAdmin());
//                    int res = ioHandler.executeSqlRequest("UPDATE person " + str + "WHERE passport_id = " +
//                            super.collection.get(id).getGroupAdmin().getPassportID());
//                    if (res == 0) {
//                        logger.info("Обновляемый элемент не соответствует формату: ");
//                        return 1;
//                    }
//                    person_id = ioHandler.getSqlResponse("SELECT id FROM person WHERE passport_id = " +
//                            el.getGroupAdmin().getPassportID()).getInt("id");
//                }
//                String str = converter.convertStudyGroupUpdate(el, person_id);
//                int res = ioHandler.executeSqlRequest("UPDATE study_group " + str + "WHERE id = " + id);
//                if(res == 0) return 1;
//                logger.info("Обновление элемента коллекции\n Старый: " + collection.get(id) + "Новый: " + el);
//                super.collection.set(id, el);
//                return 0;
//            } catch (SQLException e) {
//                return 1;
//            }
//    }
//    @Override
//    public StudyGroup getElement(int id) {
//        try{
//            return super.collection.get(id);
//        }
//        catch(Exception e){
//            return null;
//        }
//    }
//
//    @Override
//    public LinkedList<StudyGroup> getAllElements() {
//        return super.getAllElements().stream().sorted((o1, o2) -> (int)(o1.getId() - o2.getId())).collect(Collectors.toCollection(LinkedList::new));
//    }
//
//    @Override
//    public int clear() {
//        logger.info("Очистка коллекции");
//        super.collection.clear();
//        return 0;
//    }
//
//    @Override
//    public baseMetaData getmData() {
//        return super.getmData();
//    }
//
//    @Override
//    public void setmData(baseMetaData mDATA) {
//        logger.info("Смена метаданных\n" +
//                "Старые:" +
//                "Информация о коллекции:" + "\n" +
//                "Дата инициализации - " + mDATA.initDate + "\n" +
//                "Тип коллекции - " + mDATA.typeCollection + "\n" +
//                "Размер коллекции - " + mDATA.size + "\n" +
//                "Новые:" +
//                "Информация о коллекции:" + "\n" +
//                "Дата инициализации - " + mDATA.initDate + "\n" +
//                "Тип коллекции - " + mDATA.typeCollection + "\n" +
//                "Размер коллекции - " + mDATA.size + "\n");
//        super.setmData(mDATA);
//    }
//
//    @Override
//    public void setCollection(LinkedList<StudyGroup> list) {
//        logger.info("Инициализация новой коллекции");
//        super.setCollection(list);
//    }
//
//    @Override
//    public boolean checkPassportId(String passportId) {
//        List <StudyGroup> promt = super.collection.stream().
//                dropWhile(x -> x.getGroupAdmin() == null ||
//                        !Objects.equals(x.getGroupAdmin().getPassportID(), passportId)).toList();
//        if(promt.isEmpty()){
//            return false;
//        }
//        return true;
//    }
//}
