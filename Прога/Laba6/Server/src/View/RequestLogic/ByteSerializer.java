package View.RequestLogic;

import View.ResponseLogic.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ByteSerializer implements Serializer<byte[]> {
    @Override
    public byte[] serialize(Response response) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(response);
        oos.close();
        bos.close();
        return bos.toByteArray();
    }
}
