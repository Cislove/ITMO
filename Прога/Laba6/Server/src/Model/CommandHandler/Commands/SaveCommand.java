package Model.CommandHandler.Commands;

import Model.IODriver.IOHandler;
import Model.IODriver.XMLConverter.XMLCollection;
import Model.Storage.IStorage;

/**
 * Класс реализации команды "save"
 * @author Ильнар Рахимов
 */
public class SaveCommand implements Command{
    IStorage storage;
    String nameFile;
    IOHandler ioHandler;
    public SaveCommand(IStorage storage, IOHandler ioHandler){
        this.storage = storage;
        this.ioHandler = ioHandler;
        nameFile = "Server.Main.xml";
    }
    @Override
    public Pair<Integer, String> execute() {
        String response = ioHandler.writeListToFile(
                new XMLCollection(storage.getAllElements(), storage.getmData()), nameFile);
        return new Pair<>(0, response);
    }
}
