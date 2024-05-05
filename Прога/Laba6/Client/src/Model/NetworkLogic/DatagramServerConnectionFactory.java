package Model.NetworkLogic;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class DatagramServerConnectionFactory implements ServerConnectionFactory {
    @Override
    public ServerConnection initializeConnection(String host, int port) throws UnknownHostException, SocketException {
        return new DatagramServerConnection(InetAddress.getByName(host), port, 0);
    }

    @Override
    public ServerConnection initializeConnection(String host, int port, int timeout) throws UnknownHostException, SocketException {
        return new DatagramServerConnection(InetAddress.getByName(host), port, timeout);
    }
}
