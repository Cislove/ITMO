package org.example;

import com.fastcgi.FCGIInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class FastCGIHandler {
    private final float[] rValues = {1f, 2f, 3f, 4f, 5f};
    private final FCGIInterface fcgi;
    ObjectMapper objectMapper;

    public FastCGIHandler(FCGIInterface fcgi) {
        this.fcgi = fcgi;
        objectMapper = new ObjectMapper();
    }

    public void handleRequest() throws IOException {
        long startTime = System.currentTimeMillis();
        Main.logger.info("Request sleep");
        Request request;
        try {
            request = readRequestParams();
        } catch (Exception e) {
            sendError("В строке запросы должны быть переданы целочисленные значения X, Y и R (-4 <= X <= 4; -3 <= Y <= 5; 1 <= R <= 5).");
            return;
        }
        LocalDateTime currentTime = LocalDateTime.now();
        String time = currentTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        Main.logger.info("Request received: " + request);
        if (validateRequest(request)) {
            sendResponse(request.x(),
                    request.y(),
                    request.r(),
                    checkHit(request),
                    time,
                    System.currentTimeMillis() - startTime);
        } else {
            sendError("В строке запросы должны быть переданы целочисленные значения X, Y и R (-4 <= X <= 4; -3 <= Y <= 5; 1 <= R <= 5).");
//            sendResponse(0, 0, 0, false, null, 0);
        }
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
            return y >= (r - x * 2) && x <= r;
        }
        if (x >= 0 && y <= 0) {
            return y >= (-r / 2f) && x <= r;
        }
        if (x <= 0 && y >= 0) {
            if (x <= (-r / 2) || y >= (r / 2)) return false;
            return (x * x + y * y) <= (r * r);
        }
        return false;
    }

    public Request readRequestParams() throws Exception {
        try {
            String query = fcgi.request.params.getProperty("QUERY_STRING");
            Main.logger.info(query);
            float[] values = new float[]{0, 0, 0};
            Pattern.compile("&")
                    .splitAsStream(query)
                    .map(s -> s.split("="))
                    .forEach(pair -> {
                        String key = pair[0];
                        float value = Float.parseFloat(pair[1]);
                        switch (key) {
                            case "x":
                                values[0] = value;
                                break;
                            case "y":
                                values[1] = value;
                                break;
                            case "r":
                                values[2] = value;
                        }
                    });
            return new Request(values[0], values[1], values[2]);
        } catch (Exception e) {
            Main.logger.warning(e.getMessage());
            throw new Exception();
        }
    }

    public void sendResponse(float x, float y, float r, boolean result, String requestTime, long processingTime) throws JsonProcessingException {
        if (requestTime == null) {
            return;
        }
        String body = objectMapper.writeValueAsString(new Response(x, y, r, result, requestTime, processingTime));
        String httpResponse = """
                HTTP/1.1 200 OK
                Content-Type: text/html
                Content-Length: %d
                Access-Control-Allow-Origin: *
                
                %s
                """.formatted(body.getBytes().length, body);
        Main.logger.info("send" + httpResponse);
        System.out.println(httpResponse);
    }

    public void sendError(String message) {
        String httpResponse = """
                HTTP/1.1 400 Bad Request
                Content-Type: text
                Content-Length: %d
                Access-Control-Allow-Origin: *
                
                %s
                """.formatted(message.getBytes().length, message);
        Main.logger.info("send" + httpResponse);
        System.out.println(httpResponse);
    }
}
