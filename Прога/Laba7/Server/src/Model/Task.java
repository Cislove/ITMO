package Model;

import Model.CommandHandler.Commands.Pair;
import Model.CommandHandler.SwitcherServer;
import Model.RequestLogic.Request;
import Model.ResponseLogic.Response;

import java.util.concurrent.RecursiveTask;

public class Task extends RecursiveTask<Response> {
    SwitcherServer server;
    Request request;
    public Task(SwitcherServer server, Request request) {
        this.server = server;
        this.request = request;
    }
    @Override
    protected Response compute() {
        return server.execute(request);
    }
}
