package Controller;

import Model.CommandHandler.Commands.Pair;
import Model.EntryBlock;
import View.Handler;
import Model.RequestLogic.Request;
import Model.ResponseLogic.Response;

import java.io.IOException;

import static Logger.MyLogger.logger;

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
        String requestServer;
        Response responseClient;
        Request requestClient;
        do {
            try {
                requestServer = view.read();
                if(requestServer != null){
                    if(requestServer.equals("exit")){
                        responseServer = new Pair<>(-1, "");
                        view.send(responseServer.getRight());
                    }
                    //responseServer = model.execute(requestServer);
                    //view.send(responseServer.getRight());
                }
            }
            catch (IOException ignored) {
            }
            try{
                requestClient = view.acceptClient();
                if(requestClient != null){
                    System.out.println((String) requestClient.command);
                    //new Thread()
                    responseClient = model.executeServer(requestClient);
                    view.sendClient(responseClient);
                    requestClient = null;
                }
            }
            catch (Exception ignored) {

            }
        } while (responseServer.getLeft() != -1);
        view.send(responseServer.getRight());
        //model.execute("save");
        logger.info("Завершение работы сервера");
        System.exit(0);
    }
}
