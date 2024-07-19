package View;

import Model.NetworkLogic.ChanelClientConnection;
import Model.NetworkLogic.ChanelClientConnectionMT;
import Model.NetworkLogic.ClientConnection;
import Model.RequestLogic.ByteSerializer;
import Model.RequestLogic.Serializer;
import Model.ResponseLogic.Response;

import java.io.IOException;
import java.net.SocketAddress;

public class ResponderMT {
    ChanelClientConnectionMT client;
    Serializer<byte[]> serializer;
    public ResponderMT(ChanelClientConnectionMT client) {
        this.client = client;
        serializer = new ByteSerializer();
    }
    public void ConsolePrint(String s) {
        System.out.println(s);
    }

    public void sendResponse(Response response, SocketAddress userAddress) throws IOException {
        byte[] arr = serializer.serialize(response);
        client.sendData(arr, userAddress);
    }
}
