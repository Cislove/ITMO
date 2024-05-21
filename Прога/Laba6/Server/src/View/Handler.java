package View;


import Model.NetworkLogic.ChanelClientConnectionFactory;
import Model.NetworkLogic.ClientConnection;
import Model.NetworkLogic.ClientConnectionFactory;
import Model.RequestLogic.Request;
import Model.ResponseLogic.Response;

import java.io.*;

import static Logger.MyLogger.logger;

/**
 * Класс описывающий обработчик запросов к вьюхе от контроллера
 * @author Ильнар Рахимов
 */
public class Handler {
    ClientConnection client;
    ClientConnectionFactory clientFactory;
    private final Responder res;
    private final Receiver rec;
    public Handler(int port) throws IOException {
        clientFactory = new ChanelClientConnectionFactory();
        client = clientFactory.initializeConnection(port);
        rec = new Receiver(client);
        res = new Responder(client);
    }
    public void setPort(int port) throws IOException {
        logger.info("Установка порта сервера: " + port);
        client = clientFactory.initializeConnection(port);
    }
    /**
     * Метод отправляющий пользователю ответ и считывающий запрос
     * @return строку - запрос
     */
    public String update(String str) throws IOException {
        res.ConsolePrint(str);
        //System.out.println(rec.consoleIn().isEmpty());
        return rec.consoleIn();
    }
    /**
     * Метод отправляющий пользователю ответ
     */
    public void send(String str){
        res.ConsolePrint(str);
    }
    public String read() throws IOException {
        return rec.consoleIn();
    }
    public Request acceptClient() throws IOException, ClassNotFoundException {
        return rec.getRequest();
    }
    public void sendClient(Response response) throws IOException, ClassNotFoundException {
        res.sendResponse(response);
    }
}
