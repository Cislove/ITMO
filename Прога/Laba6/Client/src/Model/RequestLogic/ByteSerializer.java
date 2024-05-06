package Model.RequestLogic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ByteSerializer implements Serializer<byte[]> {
    @Override
    public byte[] serialize(Request request) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(request);
        oos.close();
        bos.close();
        return bos.toByteArray();
    }
}
