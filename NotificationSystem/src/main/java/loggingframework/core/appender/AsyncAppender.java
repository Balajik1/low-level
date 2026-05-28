package loggingframework.core.appender;

import loggingframework.core.core.LogMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

//Design pattern used Decorator
//async appender is wrapper original appender to enhance functionality
public class AsyncAppender implements Appender {
    // we need async (background) processing functionality
    //how to do: we using background thread
    private final BlockingDeque<LogMessage> queue; // <- this is thread safe>
    //we will receive lot of logs we will save them in queue and process them on our own convenience in background, without blocking main thread/original thread
    //why blocking queue : it design in situation to use producer consumer problem
    private final Appender delegate;
    private final Thread workerThread;
    private volatile boolean running = true;

    private static final int BATCH_SIZE = 50;

    public AsyncAppender(Appender delegate, int capacity) {
        this.delegate = delegate;
        this.queue = new LinkedBlockingDeque<>(capacity); //LLBLocking Queue uses two locks, one for producer and one for consumer, than ArrayBlockingQueue uses only one lock
        this.workerThread = new Thread(this::processLogs, "AsyncLogger-Worker");
        this.workerThread.setDaemon(true);
        this.workerThread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    @Override
    public void append(LogMessage logMessage) {
        try {
            queue.put(logMessage);// wait if queue is full
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // TODO, why this here and what it does
            //wake up higher thread, partial answer
        }
    }

    private void processLogs() {
        while (running || !queue.isEmpty()) {
            try {
                List<LogMessage> batch = new ArrayList<>();
                queue.drainTo(batch, BATCH_SIZE);//processing single element is costly so we are processing in batch

                if(batch.isEmpty()) {
                    batch.add(queue.take()); //take() will make it wait untill log message come (imp becoz it will make it wait untill log message come) Blocked
                }

                for (LogMessage logMessage : batch) {
                    delegate.append(logMessage);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    private void shutdown() {
        running = false; // stop processing logs
        workerThread.interrupt(); //if its blocked it will wake it
    }
}
