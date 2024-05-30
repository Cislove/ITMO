package Model.RequestLogic;

import java.io.IOException;

public interface Serializer<T> {
    T serialize(Request request) throws IOException;
}
