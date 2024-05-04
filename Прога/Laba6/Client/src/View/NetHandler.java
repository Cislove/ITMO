package View;

import Server.View.SerializeException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class NetHandler extends Handler {
    private final NetRecenetRec
    private byte[] serializer(String command, List<Object> args) throws SerializeException {
        ITransmittedPacket packet = new TransmittedPacket(command, args);
        //ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream out = new ObjectOutputStream(bos);) {
            out.writeObject(packet);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new SerializeException(e.getMessage());
        }
    }
}

