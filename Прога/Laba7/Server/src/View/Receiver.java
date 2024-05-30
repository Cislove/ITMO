package View;

import Model.NetworkLogic.ClientConnection;
import Model.RequestLogic.Request;
import Model.RequestLogic.Serializer;
import Model.ResponseLogic.ByteDeserializer;
import Model.ResponseLogic.Deserializer;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static Logger.MyLogger.logger;

/**
 * Класс описывающий приемник запросов пользователя. Имеет полный функционал для превращения запроса в необходимый вид
 * @author Ильнар Рахимов
 */
public class Receiver implements acceptable {
    ClientConnection client;
    Serializer<byte[]> serializer;
    Deserializer<byte[]> deserializer;
    private final Scanner scn = new Scanner(System.in);
    /**
     * Функция считывания запросов пользователя
     * @return строку - запрос
     */
    public Receiver(ClientConnection client) throws IOException {
        this.client = client;
        deserializer = new ByteDeserializer();
    }
    @Override
    public String consoleIn() throws IOException {
        String str = null;
        try {
            if(System.in.available() > 0){
                str = scn.nextLine();
            }
        }
        catch (NoSuchElementException e){
            logger.warning(e.getMessage());
            logger.info("Завершение работы сервера");
            System.out.println("Завершение работы");
            System.exit(0);
        }
        return str;
    }
    public Request getRequest() throws IOException, ClassNotFoundException {
        byte[] arr = client.listenAndGetData();
        return deserializer.deserialize(arr);
    }
}
