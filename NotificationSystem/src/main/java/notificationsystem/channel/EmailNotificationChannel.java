package notificationsystem.channel;

import notificationsystem.model.Notification;

public class EmailNotificationChannel implements NotificationChannel{
    @Override
    public void sendNotification(Notification notification) {
        System.out.println("Sending email notification to " + notification.getUserid() + ": " + notification.getMessage());
    }
}
