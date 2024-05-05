package Model.NetworkLogic;

import java.io.IOException;

public interface ServerConnection {
    byte[] listenAndGetData() throws IOException, ClassNotFoundException;
    void sendData(byte[] data) throws IOException;
}
