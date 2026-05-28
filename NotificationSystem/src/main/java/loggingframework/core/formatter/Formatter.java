package loggingframework.core.formatter;
import loggingframework.core.core.LogMessage;
public interface Formatter {
    String format(LogMessage logMessage);
}
