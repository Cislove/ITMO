package Model.ResponseLogic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.stream.IntStream;

public class ByteDeserializer implements Deserializer<byte[]>{

    @Override
    public Object deserialize(byte[] message) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bois = new ByteArrayInputStream(message);
        ObjectInputStream ois = new ObjectInputStream(bois);
        return ois.readObject();
    }
}
