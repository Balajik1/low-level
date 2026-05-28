package loggingframework.core.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoggerManager {
    private static  final LoggerManager INSTANCE = new LoggerManager();

    private  final Map<String, Logger> loggers;

    private LoggerManager() {
        this.loggers=new ConcurrentHashMap<>();
    }
    public static LoggerManager getInstance() {
        return INSTANCE;
    }

    public Logger getLogger(String loggerName) {
        return loggers.computeIfAbsent(loggerName, key -> new Logger(loggerName));
    }


}
