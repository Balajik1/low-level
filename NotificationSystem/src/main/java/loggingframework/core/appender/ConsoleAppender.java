package loggingframework.core.appender;

import loggingframework.core.core.LogMessage;
import loggingframework.core.formatter.Formatter;

public class ConsoleAppender implements Appender {
    private Formatter formatter;

    public  ConsoleAppender(Formatter formatter) {
        this.formatter = formatter;
    }

    void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }
    @Override
    public void append(LogMessage logMessage) {
        System.out.println(formatter.format(logMessage));
    }
}
