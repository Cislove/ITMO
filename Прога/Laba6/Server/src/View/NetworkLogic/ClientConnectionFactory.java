package View.NetworkLogic;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public interface ClientConnectionFactory {
    ClientConnection initializeConnection(int port) throws IOException;
}
