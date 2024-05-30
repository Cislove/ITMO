package Logger;

import java.io.IOException;
import java.util.logging.*;
public class MyLogger {
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
}
