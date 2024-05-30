package Model.NetworkLogic;

import java.io.IOException;
import java.net.*;

public class DatagramServerConnection implements ServerConnection {
    private final int BUFFER_SIZE = 8196;
    private final DatagramSocket socket;
    private final InetAddress address;
    private final int timeout;
    private final int port;

    public DatagramServerConnection(InetAddress address, int port, int timeout) throws SocketException {
        socket = new DatagramSocket();
        this.address = address;
        this.port = port;
        this.timeout = timeout;
    }

    @Override
    public byte[] listenAndGetData() throws IOException{
        DatagramPacket dp = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
        socket.setSoTimeout(timeout);
        socket.receive(dp);
        return dp.getData();
    }

    @Override
    public void sendData(byte[] data) throws IOException {
        //Serializer<Byte[]> serializer = new ByteSerializer();
        DatagramPacket dp = new DatagramPacket(data, data.length, address, port);
        socket.send(dp);
    }
}
