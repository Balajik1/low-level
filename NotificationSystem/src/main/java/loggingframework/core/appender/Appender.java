package loggingframework.core.appender;
import loggingframework.core.core.LogMessage;
public interface Appender {
    void append(LogMessage logMessage);
}
