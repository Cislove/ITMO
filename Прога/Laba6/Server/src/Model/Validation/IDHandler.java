package Model.Validation;

import java.util.LinkedList;

/**
 * Класс описывающий обработчик ID класса {@link Model.Storage.StorageObject.StudyGroup}
 * @author Ильнар Рахимов
 */
public class IDHandler {
    private final LinkedList <Integer> freeId;
    private int countBusyId;
    public IDHandler(){
        freeId = new LinkedList<>();
        for(int i = 1; i <= 100; i++){
            freeId.add(i);
        }
    }
    /**
     * Проверка является ли id - свободным
     * @return true - id свободен, false - id занят или список доступных id меньше, чем id
     */
    public boolean checkId(int id){
        if(countBusyId + freeId.size() < id){
            return false;
        }
        return !freeId.contains(id);
    }
    /**
     * Расширение списка доступных id
     */
    private void expandListId(){
        for(int i = countBusyId + 1; i <= countBusyId * 2; i++){
            freeId.add(i);
        }
    }
    /**
     * Проверка на доступность id. В случае свободного id, меняет его состояние на противоположное.
     * При этом список доступных ID расширяется, пока id не будет в нем умещаться
     */
    public void setId(int id) throws IDException {
        //System.out.println(freeId);
        while(id >= (countBusyId + freeId.size())){
            expandListId();
        }
        if(checkId(id)){
            throw new IDException();
        }
        //Integer newId = id;
        freeId.remove((Integer) id);
    }
    /**
     * Удаляет наименьший id из доступных
     * @return данный id
     */
    private Integer closeId(){
        countBusyId++;
        return freeId.remove(0);
    }
    /**
     * Получение наименьшего свободного id
     * @return данный id
     */
    public Integer getFreeId(){
        if(freeId.isEmpty()){
           expandListId();
        }
        return closeId();
    }
    public void openID(int id){
        countBusyId--;
        freeId.add(id);
    }
}
