package Controller;

import Model.CommandHandler.Commands.Pair;
import Model.EntryBlock;
import View.Handler;
import Model.RequestLogic.Request;
import Model.ResponseLogic.Response;

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
        view.send(responseServer.getRight());
        String requestServer = null;
        Response responseClient;
        Request requestClient;
        do {
            try {
                requestServer = view.read();
                if(requestServer != null){
                    responseServer = model.execute(requestServer);
                    view.send(responseServer.getRight());
                }
            }
            catch (IOException ignored) {
                try {
                    requestClient = view.acceptClient();
                    if (requestClient != null) {
                        responseClient = model.executeServer(requestClient);
                        view.sendClient(responseClient);
                    }
                }
                catch (Exception ign) {

                }
            }
            try{
                //System.out.println("Client accepted");
                requestClient = view.acceptClient();
                //System.out.println("Client accepted");
                if(requestClient != null){
                    //System.out.println((String)requestClient.command);
                    responseClient = model.executeServer(requestClient);
                    //System.out.println((int) responseClient.message);
                    view.sendClient(responseClient);
                    requestClient = null;
                }
            }
            catch (Exception ignored) {

            }
        } while (responseServer.getLeft() != -1);
        view.send(responseServer.getRight());
        System.exit(0);
    }
}
