package Model.CommandHandler.Commands;

import Client.Model.CommandHandler.Holders.ClosedFieldsHolder;
import Client.Model.CommandHandler.Holders.FieldHolder;
import Client.Model.Storage.IStorage;
import Client.Model.Storage.StorageObject.StudyGroup;
import Client.Model.Validation.IDHandler;
import Client.Model.Validation.Parser;
import Client.Model.Validation.Validator;

/**
 * Класс реализации команды "add"
 * @author Ильнар Рахимов
 */
public class AddCommand implements ArgumentCommand {
    private final IStorage storage;
    private FieldHolder fieldHolder;
    private final ClosedFieldsHolder closedFieldsHolder;
    public AddCommand(IStorage storage, IDHandler idHandler){
        this.storage = storage;
        fieldHolder = new FieldHolder(new Validator(storage), new Parser());
        closedFieldsHolder = new ClosedFieldsHolder(idHandler);
    }

    @Override
    public void update() {
        fieldHolder = new FieldHolder(new Validator(storage), new Parser());
    }

    @Override
    public Pair<Integer, String> execute(String arguments){
        Pair<Integer, String> response = fieldHolder.execute(arguments);
        if(response.getLeft() == 0){
            StudyGroup el = fieldHolder.getReadyEl();
            closedFieldsHolder.setFields(el);
            storage.addElement(el);
            response.setRight("Элемент успешно добавлен\n");
            return response;
        }
        return response;
    }
}
