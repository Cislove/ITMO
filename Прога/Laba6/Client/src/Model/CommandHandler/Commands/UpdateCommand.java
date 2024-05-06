package Model.CommandHandler.Commands;

import Model.CommandHandler.Holders.FieldHolder;
import Model.NetworkLogic.Handler;
import Model.RequestLogic.Request;
import Model.Storage.StorageObject.StudyGroup;
import Model.Validation.Parser;
import Model.Validation.Validator;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Класс реализации команды "update"
 * @author Ильнар Рахимов
 */
public class UpdateCommand implements ArgumentCommand {
    private Handler server;
    private FieldHolder fieldHolder;
    private Integer id;

    public UpdateCommand(Handler server){
        fieldHolder = new FieldHolder(new Validator(), new Parser());
        id = -1;
    }
    @Override
    public Pair<Integer, String> execute(String arguments){
        Pair<Integer, String> out;
        if(id == -1){
            try {
                //System.out.println(arguments);
                id = Integer.parseInt(arguments);
                if(id < 1){
                    throw new NumberFormatException();
                }
                LinkedList<Object> args = new LinkedList<>();
                args.add(id);
                int response = (int) server.sendRequestAndGetResponse(new Request("update", args));
                out = switch (response) {
                    case 0 -> fieldHolder.execute(null);
                    case 1 -> new Pair<>(0, "ID должен принадлежать элементу коллекции");
                    default -> null;
                };
            } catch (NumberFormatException e) {
                out = new Pair<>(0, "ID должен быть числом, большим нуля!\n");
            }
            catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            return out;
        }
        out = fieldHolder.execute(arguments);
        if (out.getLeft() == 0) {
            StudyGroup el = fieldHolder.getReadyEl();
            el.setId(Long.valueOf(id));
            int response;
            try {
                LinkedList<Object> args = new LinkedList<>();
                args.add(el);
                response = (int) server.sendRequestAndGetResponse(new Request("update", args));
            } catch (IOException | ClassNotFoundException e) {
                return new Pair<>(-1, "Сервер временно не доступен!\n");
            }
            switch(response){
                case 0: out.setRight("Элемент успешно добавлен\n"); break;
                case 1: out.setRight("Номер паспорта должен быть уникальным!\n"); break;
            }
//            LinkedList<StudyGroup> collection = storage.getAllElements();
//            for(int i = 0; i < collection.size(); i++){
//                if(collection.get(i).getId().equals(Long.valueOf(id))){
//                    el.setCreationDate(collection.get(i).getCreationDate());
//                    storage.updElement(i, el);
//                }
//            }
            id = -1;
        }
        return out;
    }
    @Override
    public void update() {
        fieldHolder = new FieldHolder(new Validator(), new Parser());
    }
}
