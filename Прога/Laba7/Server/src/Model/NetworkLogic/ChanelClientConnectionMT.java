package Model.NetworkLogic;

import Model.CommandHandler.Commands.Pair;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import static Logger.MyLogger.logger;

public class ChanelClientConnectionMT {
    private final int BUFFER_SIZE = (int) Math.pow(10, 9);
    private final DatagramChannel channel;
    //private SocketAddress userAddress;

    public ChanelClientConnectionMT(int port) throws IOException {
        InetSocketAddress localAddress = new InetSocketAddress(port);
        channel = DatagramChannel.open();
        channel.configureBlocking(true);
        channel.bind(localAddress);
    }

     public Pair<byte[], SocketAddress> listenAndGetData() throws IOException{
        byte[] arr = new byte[BUFFER_SIZE];
        ByteBuffer buffer = ByteBuffer.wrap(arr);
        SocketAddress userAddress = channel.receive(buffer);
        if(userAddress == null){
            throw new IOException("No user address");
        }
        logger.info("Прием пакета с адреса: " + userAddress);
        return new Pair<>(arr, userAddress);
    }

    synchronized public void sendData(byte[] data, SocketAddress userAddress) throws IOException {
        //Serializer<Byte[]> serializer = new ByteSerializer();
        ByteBuffer buffer = ByteBuffer.wrap(data);
        //System.out.println("pop2a");
        channel.send(buffer, userAddress);
        logger.info("Отправка пакета на адрес:" + userAddress);
    }
}
