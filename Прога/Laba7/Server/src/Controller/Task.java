package Controller;

import Model.ResponseLogic.Response;
import View.ResponderMT;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

public class Task implements Runnable {
    ResponderMT res;
    SocketAddress userAddress;
    Response response;
    public Task(ResponderMT res, SocketAddress userAddress, Response response) {
        this.res = res;
        this.userAddress = userAddress;
        this.response = response;
    }
    @Override
    public void run() {
        try {
            res.sendResponse(response, userAddress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
