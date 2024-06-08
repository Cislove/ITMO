package View;

import java.util.List;

public interface ITransmittedPacket {
    List<Object> getArgs();
    void setArgs(List<Object> arguments);
    String getCommand();
    void setCommand(String packetStatus);
}
