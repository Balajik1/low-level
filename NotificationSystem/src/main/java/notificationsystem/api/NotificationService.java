package notificationsystem.api;

import notificationsystem.model.Notification;

public interface NotificationService {
    public void sendNotification(Notification notification);
}
