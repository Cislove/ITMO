package View.ResponseLogic;

import java.io.Serial;
import java.io.Serializable;

public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;
    public Object message;
    public Response(Object message) {
        this.message = message;
    }
}
