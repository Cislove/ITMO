package Model.CommandHandler.Commands;

import Model.IODriver.IOHandler;
import Model.Storage.Storage;
import Model.IODriver.XMLConverter.XMLCollection;

/**
 * Класс реализации команды "save"
 * @author Ильнар Рахимов
 */
public class SaveCommand implements Command{
    Storage storage;
    String nameFile;
    IOHandler ioHandler;
    public SaveCommand(Storage storage, IOHandler ioHandler){
        this.storage = storage;
        this.ioHandler = ioHandler;
        nameFile = "Main.xml";
    }
    @Override
    public Pair<Integer, String> execute() {
        String response = ioHandler.writeListToFile(
                new XMLCollection(storage.getAllElements(), storage.getmData()), nameFile);
        return new Pair<>(0, response);
    }
}
