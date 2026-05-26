package notificationsystem.channel;

import notificationsystem.model.Notification;

public class PushNotificationChannel implements NotificationChannel{
    @Override
    public void sendNotification(Notification notification) {
        System.out.println("Sending push notification to " + notification.getUserid() + ": " + notification.getMessage());
    }
}
