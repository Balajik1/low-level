package loggingframework.core.core;

import loggingframework.core.appender.Appender;
import loggingframework.core.appender.ConsoleAppender;
import loggingframework.core.formatter.DefaultFormatter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Logger {
    private  final String loggerName;
    private volatile LogLevel logLevel;
    private  final List<Appender> appenders;

    public Logger(String loggerName) {
        this.loggerName = loggerName;
        this.logLevel = LogLevel.info;
        this.appenders = new CopyOnWriteArrayList<>();
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
