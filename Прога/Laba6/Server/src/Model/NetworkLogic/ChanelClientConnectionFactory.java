package Model.NetworkLogic;

import java.io.IOException;

public class ChanelClientConnectionFactory implements ClientConnectionFactory {
    @Override
    public ClientConnection initializeConnection(int port) throws IOException {
        return new ChanelClientConnection(port);
    }
}
