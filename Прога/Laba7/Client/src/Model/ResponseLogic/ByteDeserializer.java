package Model.ResponseLogic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.stream.IntStream;

public class ByteDeserializer implements Deserializer<byte[]>{

    @Override
    public Response deserialize(byte[] message) throws IOException, ClassNotFoundException {
        if(message.length == 0){
            return null;
        }
        ByteArrayInputStream bois = new ByteArrayInputStream(message);
        ObjectInputStream ois = new ObjectInputStream(bois);
        return (Response) ois.readObject();
    }
}
