package View;

import Model.RequestLogic.Request;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Requests {
    public static List<Request> requests = Collections.synchronizedList(new ArrayList<>());
    public static List<SocketAddress> users = Collections.synchronizedList(new ArrayList<>());
}
