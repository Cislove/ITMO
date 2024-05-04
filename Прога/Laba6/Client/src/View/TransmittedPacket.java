package View;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class TransmittedPacket implements ITransmittedPacket, Serializable {
    @Serial
    private static final long serialVersionUID = 1;
    private String command;
    private List<Object> args;
    public TransmittedPacket(String command, List<Object> args) {
        this.command = command;
        this.args = args;
    }
    public TransmittedPacket() {

    }
    public String getCommand() {
        return command;
    }

    @Override
    public void setCommand(String command) {
        this.command = command;
    }

    public List<Object> getArgs() {
        return args;
    }

    public void setArgs(List<Object> args) {
        this.args = args;
    }
}
