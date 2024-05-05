package Model.NetworkLogic;

import java.net.SocketException;
import java.net.UnknownHostException;

public interface ServerConnectionFactory {
    ServerConnection initializeConnection(String host, int port) throws UnknownHostException, SocketException;
    ServerConnection initializeConnection(String host, int port, int timeout) throws UnknownHostException, SocketException;
}
