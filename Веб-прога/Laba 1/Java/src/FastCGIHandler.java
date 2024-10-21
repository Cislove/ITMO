import com.fastcgi.FCGIInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class FastCGIHandler {
    private final float[] rValues = {1f, 2f, 3f, 4f, 5f};
    private final FCGIInterface fcgi;
    ObjectMapper objectMapper;

    public FastCGIHandler(FCGIInterface fcgi) {
        this.fcgi = fcgi;
        objectMapper = new ObjectMapper();
    }

    public void handleRequest() throws IOException {
        Date startTime = new Date();
        Request request = readRequestBody();
        if (validateRequest(request)) {
            sendResponse(request.x(),
                    request.y(),
                    request.r(),
                    checkHit(request),
                    new Date(),
                    new Date().getTime() - startTime.getTime());
        } else {
            sendResponse(0, 0, 0, false, null, 0);
        }
        //System.out.println("Hello world!");
    }

    public boolean validateRequest(Request request) {
        if (request == null) {
            return false;
        }
        if (request.x() < -4f || request.x() > 4f) {
            return false;
        }
        if (request.y() < -5f || request.y() > 3f) {
            return false;
        }
        for (float val : rValues) {
            if (val == request.r()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkHit(Request request) {
        float x = request.x();
        float y = request.y();
        float r = request.r();
        if (x < 0 && y < 0) {
            return false;
        }
        if (x > 0 && y > 0) {
            return y <= (r / 2f - x) && x <= r;
        }
        if (x >= 0 && y <= 0) {
            return y >= (r / 2f) && x <= r;
        }
        if (x <= 0 && y >= 0) {
            if (x <= (r / 2) && y >= (r / 2)) return false;
            return Math.sqrt(x * x / 2 + y * y / 2) <= (r / 2);
        }
        return false;
    }

    public Request readRequestBody() {
        try {
            fcgi.request.inStream.fill();
            var contentLength = fcgi.request.inStream.available();
            var buffer = ByteBuffer.allocate(contentLength);
            var readBytes =
                    fcgi.request.inStream.read(buffer.array(), 0,
                            contentLength);
            var requestBodyRaw = new byte[readBytes];
            buffer.get(requestBodyRaw);
            buffer.clear();
            Request request = objectMapper.readValue(new String(requestBodyRaw, StandardCharsets.UTF_8), Request.class);
//            System.out.println(request.x());
//            System.out.println(request.y());
//            System.out.println(request.r());
            return request;
        } catch (IOException e) {
            Main.logger.warning(e.getMessage());
            //e.printStackTrace();
        }
        return null;
    }

    public void sendResponse(float x, float y, float r, boolean result, Date requestTime, long processingTime) throws JsonProcessingException {
        if(requestTime == null) {
            return;
        }
        String body = objectMapper.writeValueAsString(new Response(x, y, r, result, requestTime, processingTime));
        String httpResponse = """
            HTTP/1.1 200 OK
            Content-Type: text/html
            Content-Length: %d
            Access-Control-Allow-Origin: *
            
            %s
            """.formatted(body.length(), body);
        System.out.println(httpResponse);
    }
}
