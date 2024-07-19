package Model.CommandHandler.Commands.OnServerCommands;

import Model.CommandHandler.Commands.Pair;
import Model.Storage.DataManager;
import Model.Storage.IStorage;
import Model.Storage.ObjectDescription.baseMetaData;
import Model.Storage.StorageObject.User;

/**
 * Класс реализации команды "info"
 * @author Ильнар Рахимов
 */
public class InfoCommand implements Command{
    DataManager dataManager;
    public InfoCommand(DataManager dataManager){
        this.dataManager = dataManager;
    }
    public Pair<Integer, String> execute(User user){
        baseMetaData data = dataManager.getmData();
        String s = "Информация о коллекции:" + "\n";
        s += "Дата инициализации - " + data.initDate + "\n";
        s += "Тип коллекции - " + data.typeCollection + "\n";
        s += "Размер коллекции - " + data.size + "\n";
        return new Pair<>(0, s);
    }
}
