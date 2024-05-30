package Model.ResponseLogic;

import java.io.IOException;

public interface Deserializer<T>{
    Response deserialize(T message) throws IOException, ClassNotFoundException;
}
