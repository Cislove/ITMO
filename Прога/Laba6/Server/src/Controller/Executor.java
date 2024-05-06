package Controller;

import Model.CommandHandler.Commands.Pair;
import Model.EntryBlock;
import View.Handler;
import View.RequestLogic.Request;
import View.ResponseLogic.Response;

import java.io.IOException;

/**
 * Класс исполнитель всей программы. Отвечает за связывание модели и вьюхи
 * @author Ильнар Рахимов
 */
public class Executor implements IExecutor {
    final private EntryBlock model;
    final private Handler view;
    public Executor() throws IOException{
        model = new EntryBlock();
        view = new Handler(6597);
    }
    /**
     * Метод реализующий исполнение программы
     */
    @Override
    public void execute() {
        Pair<Integer, String> responseServer = model.start();
        String requestServer;
        Response responseClient;
        Request requestClient;
        do {
            try {
                requestServer = view.update(responseServer.getRight());
                responseServer = model.execute(requestServer);
            }
            catch (Exception ignored) {

            }
            try{
                requestClient = view.acceptClient();
                responseClient = model.execute(requestClient);
                view.sendClient(responseClient);
            }
            catch (Exception ignored) {

            }
        } while (response.getLeft() != -1);
        view.send(response.getRight());
        System.exit(0);
    }
}
