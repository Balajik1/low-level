package loggingframework.core.demo;

import loggingframework.core.appender.ConsoleAppender;
import loggingframework.core.core.LogLevel;
import loggingframework.core.core.Logger;
import loggingframework.core.core.LoggerManager;
import loggingframework.core.formatter.DefaultFormatter;

public class LoggingDemo {
    public static void main(String[] args) {
        LoggerManager loggerManager = LoggerManager.getInstance();
        Logger logger = loggerManager.getLogger(LoggingDemo.class.getName());
        logger.setLogLevel(LogLevel.info);
        logger.addAppender(new ConsoleAppender(new DefaultFormatter()));

        logger.debug("This is error message");
    }
}
