package Model.CommandHandler.Commands;

import Model.Storage.IStorage;
import Model.Storage.ObjectDescription.baseMetaData;

/**
 * Класс реализации команды "info"
 * @author Ильнар Рахимов
 */
public class InfoCommand implements Command{
    IStorage storage;
    public InfoCommand(IStorage storage){
        this.storage = storage;
    }
    public Pair<Integer, String> execute(){
        baseMetaData data = storage.getmData();
        String s = "Информация о коллекции:" + "\n";
        s += "Дата инициализации - " + data.initDate + "\n";
        s += "Тип коллекции - " + data.typeCollection + "\n";
        s += "Размер коллекции - " + data.size + "\n";
        return new Pair<>(0, s);
    }
}
