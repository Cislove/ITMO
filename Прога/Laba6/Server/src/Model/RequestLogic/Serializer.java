package Model.RequestLogic;

import Model.ResponseLogic.Response;

import java.io.IOException;

public interface Serializer<T> {
    T serialize(Response response) throws IOException;
}
