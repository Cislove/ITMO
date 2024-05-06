package View.NetworkLogic;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ChanelClientConnectionFactory implements ClientConnectionFactory {
    @Override
    public ClientConnection initializeConnection(int port) throws IOException {
        return new ChanelClientConnection(port);
    }
}
