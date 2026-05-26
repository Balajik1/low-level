package notificationsystem.factory;
import notificationsystem.channel.*;
import notificationsystem.model.ChannelType;
public class NotificationChannelFactory {

    public static NotificationChannel getNotificationChannel(ChannelType channelType) {
        return switch (channelType) {
            case SMS -> new SmsNotificationChannel();
            case EMAIL -> new EmailNotificationChannel();
            case PUSH -> new PushNotificationChannel();
            default -> throw new IllegalArgumentException("Unsupported channel type: " + channelType);
        };
    }
}
