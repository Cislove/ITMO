package View;


import View.NetworkLogic.ChanelClientConnectionFactory;
import View.NetworkLogic.ClientConnection;
import View.NetworkLogic.ClientConnectionFactory;
import View.RequestLogic.Request;
import View.ResponseLogic.Response;

import java.io.*;

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
        client = clientFactory.initializeConnection(port);
    }
    /**
     * Метод отправляющий пользователю ответ и считывающий запрос
     * @return строку - запрос
     */
    public String update(String str) throws IOException {
        res.ConsolePrint(str);
        return rec.consoleIn();
    }
    /**
     * Метод отправляющий пользователю ответ
     */
    public void send(String str){
        res.ConsolePrint(str);
    }
    public Request acceptClient() throws IOException, ClassNotFoundException {
        return rec.getRequest();
    }
    public void sendClient(Response response) throws IOException, ClassNotFoundException {
        res.sendResponse(response);
    }
}
