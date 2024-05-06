package Model.CommandHandler.Commands;

import Model.CommandHandler.Holders.FieldHolder;
import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;
import Model.Storage.IStorage;
import Model.Storage.StorageObject.StudyGroup;
import Model.Validation.IDHandler;
import Model.Validation.Parser;
import Model.Validation.Validator;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Класс реализации команды "add"
 * @author Ильнар Рахимов
 */
public class AddCommand implements ArgumentCommand {
    private Handler server;
    private FieldHolder fieldHolder;
    //private final ClosedFieldsHolder closedFieldsHolder;
    public AddCommand(Handler server){
        this.server = server;
        fieldHolder = new FieldHolder(new Validator(), new Parser());
        //closedFieldsHolder = new ClosedFieldsHolder(idHandler);
    }

    @Override
    public void update() {
        fieldHolder = new FieldHolder(new Validator(), new Parser());
    }

    @Override
    public Pair<Integer, String> execute(String arguments){
        Pair<Integer, String> out = fieldHolder.execute(arguments);
        if(out.getLeft() == 0){
            StudyGroup el = fieldHolder.getReadyEl();
            LinkedList<Object> args = new LinkedList<>();
            args.add(el);
            int response = 0;
            try {
                response = (int) server.sendRequestAndGetResponse(new Request("add", args));
                switch(response){
                    case 0: out.setRight("Элемент успешно добавлен\n"); break;
                    case 1: out.setRight("Номер паспорта должен быть уникальным!\n"); break;
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return out;
    }
}
