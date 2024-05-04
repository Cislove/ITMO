package View;

import java.io.Serializable;
import java.util.List;

public class ReceivedPacket implements Serializable {
    public String command;
    public List<Object> args;
    public ReceivedPacket(String command, List<Object> args) {
        this.command = command;
        this.args = args;
    }
}
