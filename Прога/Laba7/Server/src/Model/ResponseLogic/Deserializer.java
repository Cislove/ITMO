package Model.ResponseLogic;

import Model.RequestLogic.Request;

import java.io.IOException;

public interface Deserializer<T>{
    Request deserialize(T message) throws IOException, ClassNotFoundException;
}
