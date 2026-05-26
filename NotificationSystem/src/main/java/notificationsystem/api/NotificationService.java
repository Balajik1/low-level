package notificationsystem.api;

import notificationsystem.model.Notification;
import notificationsystem.service.NotificationDispatcher;

public class NotificationService {
   private final NotificationDispatcher notificationDispatcher;

    public NotificationService(NotificationDispatcher notificationDispatcher) {
        this.notificationDispatcher = notificationDispatcher;
    }

    public void sendNotification(Notification notification) {
        notificationDispatcher.dispatch(notification);
    }
}
