package View;

import Model.CommandHandler.Commands.Pair;
import Model.NetworkLogic.ChanelClientConnectionMT;
import Model.NetworkLogic.ClientConnection;
import Model.RequestLogic.Request;
import Model.RequestLogic.Serializer;
import Model.ResponseLogic.ByteDeserializer;
import Model.ResponseLogic.Deserializer;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static Logger.MyLogger.logger;

public class ReceiverMT {
    ChanelClientConnectionMT client;
    Deserializer<byte[]> deserializer;
    private final Scanner scn = new Scanner(System.in);
    /**
     * Функция считывания запросов пользователя
     * @return строку - запрос
     */
    public ReceiverMT(ChanelClientConnectionMT client) throws IOException {
        this.client = client;
        deserializer = new ByteDeserializer();
    }

    public Pair<Request, SocketAddress> getRequest() throws IOException, ClassNotFoundException {
        Pair<byte[], SocketAddress> result = client.listenAndGetData();
        //System.out.println("Принял2\n");
        return new Pair<>(deserializer.deserialize(result.getLeft()), result.getRight());
    }
}
