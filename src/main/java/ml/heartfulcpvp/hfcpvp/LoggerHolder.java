package ml.heartfulcpvp.hfcpvp;

import java.util.logging.Logger;

public class LoggerHolder {
    private static Logger logger;

    public static void setLogger(Logger logger) {
        LoggerHolder.logger = logger;
    }

    public static Logger getLogger() {
        return logger;
    }
}
