package View.RequestLogic;

import View.ResponseLogic.Response;

import java.io.IOException;

public interface Serializer<T> {
    T serialize(Response response) throws IOException;
}
