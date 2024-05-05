package Model.RequestLogic;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 2;
    public String command;
    public List<Object> args;
    public Request(String command, List<Object> args) {
        this.command = command;
        this.args = args;
    }
}
