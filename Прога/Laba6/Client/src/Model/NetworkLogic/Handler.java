package Model.NetworkLogic;

import Model.RequestLogic.ByteSerializer;
import Model.RequestLogic.Request;
import Model.RequestLogic.Serializer;
import Model.ResponseLogic.ByteDeserializer;
import Model.ResponseLogic.Deserializer;
import Model.ResponseLogic.Response;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Handler {
    ServerConnectionFactory serverFactory;
    ServerConnection server;
    Serializer<byte[]> serializer;
    Deserializer<byte[]> deserializer;
    public Handler(){
        serverFactory = new DatagramServerConnectionFactory();
        serializer = new ByteSerializer();
        deserializer = new ByteDeserializer();
    }

    public void setServer(String host, int port) throws SocketException, UnknownHostException {
        server = serverFactory.initializeConnection(host, port, 5000);
    }

    public Object getResponse() throws IOException, ClassNotFoundException {
        byte[] arr = server.listenAndGetData();
        return deserializer.deserialize(arr).message;
    }
    public void sendRequest(Request request) throws IOException {
        byte[] arr = serializer.serialize(request);
        server.sendData(arr);
    }
    public Object sendRequestAndGetResponse(Request request) throws IOException, ClassNotFoundException {
        sendRequest(request);
        return getResponse();
    }
}
