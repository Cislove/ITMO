package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ControllerServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    protected void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String INVALID_DATA = "Неправильный формат данных";
        try {
            long startTime = System.currentTimeMillis();
            double x = Double.parseDouble(req.getParameter("x"));
            double y = Double.parseDouble(req.getParameter("y"));
            double r = Double.parseDouble(req.getParameter("r"));

            req.setAttribute("x", x);
            req.setAttribute("y", y);
            req.setAttribute("r", r);
            req.setAttribute("time", startTime);
            req.getRequestDispatcher("/Checker").forward(req, resp);
        }
        catch (Exception e) {
            sendError(resp, INVALID_DATA);
            resp.getWriter().write("Неправильные данные: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void sendError(HttpServletResponse resp, String errMessage) throws IOException {
        resp.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
        resp.setContentType("application/json");
        resp.getWriter().write("Неправильные данные: " + errMessage);
    }
}
