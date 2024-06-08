package Model.NetworkLogic;

import java.io.IOException;
import java.net.InetAddress;

public interface ClientConnection {
    byte[] listenAndGetData() throws IOException, ClassNotFoundException;
    void sendData(byte[] data) throws IOException;
}
