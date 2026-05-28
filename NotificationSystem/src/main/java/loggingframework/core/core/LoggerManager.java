package loggingframework.core.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//Followed Singleton pattern
public class LoggerManager {
    private static  final LoggerManager INSTANCE = new LoggerManager();

    private  final Map<String, Logger> loggers;

    private LoggerManager() {
        this.loggers=new ConcurrentHashMap<>();// as multiple threads may access loggerManager at the same time as sharing single instance of logManager
        //loggerManager has map which shared across multiple threads and this map should be thread safe therefore we use ConcurrentHashMap
    }
    public static LoggerManager getInstance() {
        return INSTANCE;
    }

    public Logger getLogger(String loggerName) {
        return loggers.computeIfAbsent(loggerName, key -> new Logger(loggerName));
        //Q> why we are not synchronized here?
        //ans : we used concurrentHashMap which is thread safe, it applies synchronization on bucket level
        // if multiple req come to get logger with diff name and here diff name stored in diff bucket  and they can be executed parallelly and bucket level synchronization will handle it
        // and if I synchronized here , all the thread access map one by one, and damage the throughput
    }


}
