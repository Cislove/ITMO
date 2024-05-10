package View;

import Model.NetworkLogic.ClientConnection;
import Model.RequestLogic.ByteSerializer;
import Model.RequestLogic.Serializer;
import Model.ResponseLogic.Response;

import java.io.IOException;

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
