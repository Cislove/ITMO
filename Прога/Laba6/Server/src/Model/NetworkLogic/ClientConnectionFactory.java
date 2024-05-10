package Model.NetworkLogic;

import java.io.IOException;

public interface ClientConnectionFactory {
    ClientConnection initializeConnection(int port) throws IOException;
}
