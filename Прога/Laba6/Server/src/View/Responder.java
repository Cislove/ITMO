package View;

import View.NetworkLogic.ClientConnection;
import View.NetworkLogic.ClientConnectionFactory;
import View.RequestLogic.ByteSerializer;
import View.RequestLogic.Serializer;
import View.ResponseLogic.Deserializer;
import View.ResponseLogic.Response;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Класс описывающий передатчик ответов пользователю. Имеет полный функционал для превращения готового ответа в необходимый вид
 * @author Ильнар Рахимов
 */
public class Responder {
    ClientConnection client;
    Serializer<byte[]> serializer;
    public Responder(ClientConnection client) {
        this.client = client;
        serializer = new ByteSerializer();
    }
    public void ConsolePrint(String s) {
        System.out.println(s);
    }

    public void sendResponse(Response response) throws IOException{
        byte[] arr = serializer.serialize(response);
        client.sendData(arr);
    }
}
