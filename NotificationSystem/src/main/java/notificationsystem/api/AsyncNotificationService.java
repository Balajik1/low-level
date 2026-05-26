package notificationsystem.api;

import notificationsystem.model.Notification;
import notificationsystem.service.NotificationDispatcher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncNotificationService implements NotificationService {
    private final NotificationDispatcher NotificationDispatcher;
    private final ExecutorService executorService;

    public AsyncNotificationService(NotificationDispatcher dispatcher){
        this.NotificationDispatcher = dispatcher;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    public void sendNotification(Notification notification){
        executorService.submit(() -> NotificationDispatcher.dispatch(notification));
    }
}
