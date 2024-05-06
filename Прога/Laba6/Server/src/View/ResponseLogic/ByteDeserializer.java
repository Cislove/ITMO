package View.ResponseLogic;

import View.RequestLogic.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ByteDeserializer implements Deserializer<byte[]>{

    @Override
    public Request deserialize(byte[] message) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bois = new ByteArrayInputStream(message);
        ObjectInputStream ois = new ObjectInputStream(bois);
        return (Request) ois.readObject();
    }
}
