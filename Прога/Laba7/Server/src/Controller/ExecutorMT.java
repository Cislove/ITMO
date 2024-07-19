package Controller;

import Model.CommandHandler.Commands.Pair;
import Model.EntryBlock;
import Model.NetworkLogic.ChanelClientConnectionMT;
import View.Handler;
import Model.RequestLogic.Request;
import Model.ResponseLogic.Response;
import View.ReceiverMT;
import View.ResponderMT;

import java.io.IOException;
import java.net.SocketAddress;

import static Logger.MyLogger.logger;

/**
 * Класс исполнитель всей программы. Отвечает за связывание модели и вьюхи
 * @author Ильнар Рахимов
 */
public class ExecutorMT{
    final private EntryBlock model;
    final private Handler view;
    ReceiverMT rec;
    ResponderMT res;
    public ExecutorMT() throws IOException{
        model = new EntryBlock();
        view = new Handler(6598);
        ChanelClientConnectionMT client = new ChanelClientConnectionMT(6597);
        rec = new ReceiverMT(client);
        res = new ResponderMT(client);
    }
    /**
     * Метод реализующий исполнение программы
     */
    public void execute() throws IOException {
        Pair<Integer, String> responseServer = model.start();
        view.send(responseServer.getRight());
        String requestServer;
        //Response responseClient;
        Pair<Request, SocketAddress> requestClient;
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
                requestClient = rec.getRequest();
                if(requestClient != null){
                    //System.out.println((String) requestClient.getLeft().command);
                    new Thread(new ExecutionThread(rec, res, model, requestClient.getRight(), requestClient.getLeft())).start();
                }
            }
            catch (Exception ignored) {

            }
        } while (responseServer.getLeft() != -1);
        view.send(responseServer.getRight());
        model.execute("save");
        logger.info("Завершение работы сервера");
        System.exit(0);
    }
}