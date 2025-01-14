package Model.CommandHandler.Commands.OnServerCommands;

import Model.CommandHandler.Commands.Pair;
import Model.CommandHandler.Holders.ClosedFieldsHolder;
import Model.CommandHandler.Holders.FieldHolder;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Storage.StorageObject.User;
import Model.Validation.IDHandler;
import Model.Validation.Parser;
import Model.Validation.Validator;

/**
 * Класс реализации команды "add"
 * @author Ильнар Рахимов
 */
public class AddCommand implements ArgumentCommand{
    private final IStorage storage;
    private FieldHolder fieldHolder;
    private final ClosedFieldsHolder closedFieldsHolder;
    public AddCommand(IStorage storage, IDHandler idHandler){
        this.storage = storage;
        fieldHolder = new FieldHolder(new Validator(storage), new Parser());
        closedFieldsHolder = new ClosedFieldsHolder();
    }

    @Override
    public void update() {
        fieldHolder = new FieldHolder(new Validator(storage), new Parser());
    }

    @Override
    public Pair<Integer, String> execute(User user, String arguments){
        Pair<Integer, String> response = fieldHolder.execute(arguments);
        if(response.getLeft() == 0){
            StudyGroup el = fieldHolder.getReadyEl();
            closedFieldsHolder.setFields(el);
            //storage.addElement(el);
            response.setRight("Элемент успешно добавлен\n");
            return response;
        }
        return response;
    }
}