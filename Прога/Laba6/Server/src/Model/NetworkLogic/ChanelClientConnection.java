package Model.NetworkLogic;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ChanelClientConnection implements ClientConnection {
    private final int BUFFER_SIZE = 8196;
    private final DatagramChannel channel;
    private SocketAddress userAddress;

    public ChanelClientConnection(int port) throws IOException {
        InetSocketAddress localAddress = new InetSocketAddress(port);
        channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.bind(localAddress);
    }

    @Override
    public byte[] listenAndGetData() throws IOException{
        byte[] arr = new byte[BUFFER_SIZE];
        ByteBuffer buffer = ByteBuffer.wrap(arr);
        userAddress = channel.receive(buffer);
        if(userAddress == null){
            throw new IOException("No user address");
        }
        buffer.flip();
        return arr;
    }

    @Override
    public void sendData(byte[] data) throws IOException {
        //Serializer<Byte[]> serializer = new ByteSerializer();
        ByteBuffer buffer = ByteBuffer.wrap(data);
        //System.out.println("pop2a");
        channel.send(buffer, userAddress);
    }
}
