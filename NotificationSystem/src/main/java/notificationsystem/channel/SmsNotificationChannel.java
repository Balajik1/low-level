package notificationsystem.channel;

import notificationsystem.model.Notification;

public class SmsNotificationChannel implements  NotificationChannel {
    @Override
    public void sendNotification(Notification notification) {
        System.out.println("Sending SMS notification to " + notification.getUserid() + ": " + notification.getMessage());
    }
}
