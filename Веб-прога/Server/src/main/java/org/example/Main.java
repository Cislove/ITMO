package org.example;

import com.fastcgi.FCGIInterface;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Main {
    public static java.util.logging.Logger logger = java.util.logging.Logger.getLogger("MyLog");

    static {
        logger.setUseParentHandlers(false);
        try {
            FileHandler fh = new FileHandler("log.txt", true);
            logger.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
            logger.info("Logger initialized");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            FCGIInterface fcgiInterface = new FCGIInterface();
            FastCGIHandler handler = new FastCGIHandler(fcgiInterface);
            while (true) {
                try {
                    if(fcgiInterface.FCGIaccept() >= 0)
                        handler.handleRequest();
                } catch (Exception e) {
                    //logger.warning(e.getMessage());
                    //e.printStackTrace();
                }
            }
        }
        catch (Exception e){
            logger.warning(e.getMessage());
        }

    }

}