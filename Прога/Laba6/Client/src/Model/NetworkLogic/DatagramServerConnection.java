package Model.NetworkLogic;

import java.io.IOException;
import java.net.*;

public class DatagramServerConnection implements ServerConnection {
    private final int BUFFER_SIZE = 8196;
    private final DatagramSocket socket;
    private final int timeout;
    private final SocketAddress address;

    public DatagramServerConnection(InetAddress address, int port, int timeout) throws SocketException {
        socket = new DatagramSocket(port);
        this.timeout = timeout;
        this.address = new InetSocketAddress(address, port);
    }

    @Override
    public byte[] listenAndGetData() throws IOException, ClassNotFoundException{
        //Deserializer <Byte[]> deserializer = new ByteDeserializer();
        DatagramPacket dp = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
        socket.setSoTimeout(timeout);
        //socket.receive(dp);
        //Byte[] arr = new Byte[dp.getData().length];
        //IntStream.range(0, arr.length).forEach(i -> arr[i] = dp.getData()[i]);
        socket.receive(dp);
        return dp.getData();
    }

    @Override
    public void sendData(byte[] data) throws IOException {
        //Serializer<Byte[]> serializer = new ByteSerializer();
        DatagramPacket dp = new DatagramPacket(data, data.length, address);
        socket.send(dp);
    }
}
