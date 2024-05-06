package View;

import View.NetworkLogic.ChanelClientConnectionFactory;
import View.NetworkLogic.ClientConnection;
import View.NetworkLogic.ClientConnectionFactory;
import View.RequestLogic.ByteSerializer;
import View.RequestLogic.Request;
import View.RequestLogic.Serializer;
import View.ResponseLogic.ByteDeserializer;
import View.ResponseLogic.Deserializer;
import View.ResponseLogic.Response;

import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
        String str = "";
        try {
            if(System.in.available() > 0){
                str = scn.nextLine();
            }
        }
        catch (NoSuchElementException e){
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
