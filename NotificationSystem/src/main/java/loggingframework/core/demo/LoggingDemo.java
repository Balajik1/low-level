package loggingframework.core.demo;

import loggingframework.core.appender.ConsoleAppender;
import loggingframework.core.core.LogLevel;
import loggingframework.core.core.Logger;
import loggingframework.core.core.LoggerManager;
import loggingframework.core.formatter.DefaultFormatter;

public class LoggingDemo {
    public static void main(String[] args) {
        //Note : at each class you should discuss the thread safety, it keeps good impact
        LoggerManager loggerManager = LoggerManager.getInstance();
        Logger logger = loggerManager.getLogger(LoggingDemo.class.getName());
        logger.setLogLevel(LogLevel.info);
        logger.addAppender(new ConsoleAppender(new DefaultFormatter()));

        logger.debug("This is error message");

        //ideally we should not define logger synchronously
        // we need to manage logging asyn way
        /*
            ex:  //imp task
            logger.debug("This is error message");
            //imp task

            now due to logging my payment(making a payment more imp than logging), yes logging also imp but you should not block payment due to logging, so we need to wait till the log call complemeted by thread, instead of this we can use async logging
         */
    }
}
