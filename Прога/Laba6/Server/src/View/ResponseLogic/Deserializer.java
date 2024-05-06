package View.ResponseLogic;

import View.RequestLogic.Request;

import java.io.IOException;

public interface Deserializer<T>{
    Request deserialize(T message) throws IOException, ClassNotFoundException;
}
