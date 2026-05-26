package notificationsystem.channel;

import notificationsystem.model.Notification;

public interface NotificationChannel {
    void sendNotification(Notification notification);
}
