package Controller;

import Model.CommandHandler.Commands.Pair;
import Model.EntryBlock;
import Model.NetworkLogic.ChanelClientConnectionMT;
import Model.RequestLogic.Request;
import Model.ResponseLogic.Response;
import View.Handler;
import View.Receiver;
import View.ReceiverMT;
import View.ResponderMT;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutionThread implements Runnable {
    ReceiverMT rec;
    ResponderMT res;
    EntryBlock model;
    SocketAddress userAddress;
    Request request;
    public ExecutionThread(ReceiverMT rec, ResponderMT res, EntryBlock model, SocketAddress userAddress, Request request) throws IOException {
        this.rec = rec;
        this.res = res;
        this.model = model;
        this.userAddress = userAddress;
        this.request = request;
    }
    @Override
    public void run() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println((String) request.command);
        Response responseClient = model.executeServer(request);
        executorService.submit(new Task(res, userAddress, responseClient));
        //res.sendResponse(responseClient, userAddress);/
    }
}
