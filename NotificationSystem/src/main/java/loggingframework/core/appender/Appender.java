package loggingframework.core.appender;
import loggingframework.core.core.LogMessage;
//Followed Strategy pattern
public interface Appender {
    void append(LogMessage logMessage);
}
