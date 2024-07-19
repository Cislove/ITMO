package Model.ResponseLogic;

import Model.RequestLogic.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ByteDeserializer implements Deserializer<byte[]>{

    @Override
    public Request deserialize(byte[] message) throws IOException, ClassNotFoundException {
        try {
            ByteArrayInputStream bois = new ByteArrayInputStream(message);
            ObjectInputStream ois = new ObjectInputStream(bois);
            //System.out.println("принял3\n");
            return (Request) ois.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
