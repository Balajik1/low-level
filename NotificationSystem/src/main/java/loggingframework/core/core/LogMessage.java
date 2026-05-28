package loggingframework.core.core;

import java.time.LocalDateTime;

/**
 * this class is dont have any setter and its variables are final
 * its immutable and thread safe

 */
public class LogMessage {
    private final  String message;
    private  final LogLevel logLevel;
    private  final LocalDateTime timestamp;
    private  final  String threadName;
    private final String loggerName;

    public LogMessage(String message, LogLevel logLevel, String loggerName) {
        this.message = message;
        this.logLevel = logLevel;
        this.timestamp = LocalDateTime.now();
        this.threadName = Thread.currentThread().getName();
        this.loggerName = loggerName;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public String getThreadName() {
        return threadName;
    }

    public LocalDateTime getTimeStamp() {
        return timestamp;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public String getMessage() {
        return message;
    }
}
