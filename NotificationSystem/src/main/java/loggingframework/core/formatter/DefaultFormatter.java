package loggingframework.core.formatter;

import loggingframework.core.core.LogMessage;

import java.time.format.DateTimeFormatter;

public class DefaultFormatter implements Formatter {
    private  final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public String format(LogMessage logMessage) {
        return String.format("%s [%s] [%s] %s", dateTimeFormatter.format(logMessage.getTimeStamp()), logMessage.getLogLevel(), logMessage.getLoggerName(), logMessage.getMessage());
    }
}
