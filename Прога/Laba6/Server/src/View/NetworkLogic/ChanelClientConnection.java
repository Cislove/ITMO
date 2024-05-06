package View.NetworkLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ChanelClientConnection implements ClientConnection {
    private final int BUFFER_SIZE = 8196;
    private final DatagramChannel channel;
    private final InetSocketAddress localAddress;
    private SocketAddress userAddress;
    private ByteBuffer buffer;

    public ChanelClientConnection(int port) throws IOException {
        this.localAddress = new InetSocketAddress(port);
        channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.bind(localAddress);
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
    }

    @Override
    public byte[] listenAndGetData() throws IOException{
        DatagramPacket dp = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
        buffer.clear();
        userAddress = channel.receive(buffer);
        if(userAddress == null){
            throw new IOException("No user address");
        }
        return buffer.array();
    }

    @Override
    public void sendData(byte[] data) throws IOException {
        //Serializer<Byte[]> serializer = new ByteSerializer();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        buffer.clear();
        channel.send(buffer, userAddress);
    }
}
