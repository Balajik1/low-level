package notificationsystem.api;

import notificationsystem.model.Notification;
import notificationsystem.service.NotificationDispatcher;

public class SyncNotificationService  implements NotificationService{
   private final NotificationDispatcher notificationDispatcher;

    public SyncNotificationService(NotificationDispatcher notificationDispatcher) {
        this.notificationDispatcher = notificationDispatcher;
    }

    public void sendNotification(Notification notification) {
        notificationDispatcher.dispatch(notification);
    }
}
