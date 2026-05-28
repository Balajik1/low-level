package loggingframework.core.core;

import loggingframework.core.appender.Appender;
import loggingframework.core.appender.ConsoleAppender;
import loggingframework.core.formatter.DefaultFormatter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Logger {
    private  final String loggerName;
    private volatile LogLevel logLevel; // log level can be changed, as it used by many threads, and to have it up to date (changes to reflect to other thread) we use volatile
    private  final List<Appender> appenders;

    public Logger(String loggerName) {
        this.loggerName = loggerName;
        this.logLevel = LogLevel.info;
        this.appenders = new CopyOnWriteArrayList<>(); //Q. why copyOnWriteArrayList is used here? to avoid ConcurrentModificationException (Thread safety)
        // as we can add appender dynamically be many threads and while adding or removing  and some thread it iterating on the list and may cause ConcurrentModificationException
        // so what it does, while writting, it will write to a new array, and then replace the old array with the new array, so that it will not cause ConcurrentModificationException
        //Q. can  u use it in any situation?  No, we can use only when there is lot of read and less modification., not even useful in 70-30(Write) use case
        //for logger system it sits in perfectly
    }

    public void addAppender(Appender appender) {
        appenders.add(appender);
    }
    public void removeAppender(Appender appender) {
        appenders.remove(appender);
    }
    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public void log(LogLevel logLevel, String message) {
        if(logLevel.getLevel() < this.logLevel.getLevel()) return;
        LogMessage logMessage = new LogMessage(message, logLevel, loggerName);

        for (Appender appender : appenders) {
            appender.append(logMessage);
        }
    }

    public void debug(String message) {
        log(LogLevel.debug, message);
    }

    public void info(String message) {
        log(LogLevel.info, message);
    }

    public void warning(String message) {
        log(LogLevel.warning, message);
    }

    public void error(String message) {
        log(LogLevel.error, message);
    }

    public void fatal(String message) {
        log(LogLevel.fatal, message);
    }
}
