package loggingframework.core.demo;

import loggingframework.core.appender.AsyncAppender;
import loggingframework.core.appender.ConsoleAppender;
import loggingframework.core.appender.FileAppender;
import loggingframework.core.core.LogLevel;
import loggingframework.core.core.Logger;
import loggingframework.core.core.LoggerManager;
import loggingframework.core.formatter.DefaultFormatter;
import loggingframework.core.formatter.Formatter;

public class AsynAppenderDemo {
    public static void main(String[] args) throws InterruptedException {
        Logger logger = LoggerManager.getInstance().getLogger(AsynAppenderDemo.class.getName());
        logger.setLogLevel(LogLevel.debug);

        //create formatter
        Formatter formatter = new DefaultFormatter();

        //create appender
        FileAppender fileAppender= new FileAppender("async.log", formatter);
        ConsoleAppender consoleAppender = new ConsoleAppender(formatter);

        //wrap file appender with async appender
        AsyncAppender asyncAppender = new AsyncAppender(fileAppender, 10000);

        //add appender to logger
//        logger.addAppender(consoleAppender);
        logger.addAppender(asyncAppender);

        System.out.println("starting asyns log demo");
        long start=System.currentTimeMillis();
        //create multiple task to simulate real load

        Runnable task= ()-> {
            for (int i = 0; i < 100; i++) {
                logger.debug("Message "+i+" from thread "+Thread.currentThread().getName());
            }
        };

        Thread t1 = new Thread(task,"Worker-1");
        Thread t2 = new Thread(task,"Worker-2");
        Thread t3 = new Thread(task,"Worker-3");
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        long end=System.currentTimeMillis();

        System.out.println("time taken : "+(end-start)+" ms");

        //give time to async worked to finnish remaining logs
        Thread.sleep(2000);

        System.out.println("done");


    }
}
