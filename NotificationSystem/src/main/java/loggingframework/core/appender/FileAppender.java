package loggingframework.core.appender;

import loggingframework.core.core.LogMessage;
import loggingframework.core.formatter.Formatter;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileAppender implements Appender {
    private final Formatter formatter;
    private final BufferedWriter writer;
    private final Object lock=new Object();

    public FileAppender(String filePath, Formatter formatter) {
        this.formatter = formatter;
        try {
            this.writer = Files.newBufferedWriter(Paths.get(filePath));
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize FileAppender", e);
        }

        // its is very imp hook -> SHUTDOWN HOOK
        //when ur java application is getting terminated(getting shut down) execute this task. we telling JVM to execute this method
        //basically executing thread that shut down , closing writer of the file
        //reason : basically we wanted to gracefully shut down our application, when you have file writter stream open and your application about to be terminated
        // file writing may be in process, so we want to close it gracefully
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutDown));
    }
    @Override
    public void append(LogMessage logMessage) {
        //if multiple trying to write at the same time then this lock will take care
        //writing should happen one log at a time
        synchronized (lock) {
            try {
                writer.write(formatter.format(logMessage));
                writer.newLine();
                writer.flush();
                // flush is costly operation, and why we doing for each and every log ?
                //ans : yes, it will impact the throughput of system, just to keep impl simple for impl now, we are flushing after each log
                // better approach will be flush it periodically (can define scheduler)
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to write to FileAppender", e);
            }
        }
    }

    private void shutDown() {
        synchronized (lock) {
            try {
                writer.flush();
                writer.close();
            } catch (Exception e) {
                throw new RuntimeException("Failed to Close FileAppender", e);
            }
        }
    }
}
