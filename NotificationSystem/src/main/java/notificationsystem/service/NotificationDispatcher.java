package notificationsystem.service;

import notificationsystem.channel.NotificationChannel;
import notificationsystem.factory.NotificationChannelFactory;
import notificationsystem.model.ChannelType;
import notificationsystem.model.Notification;
import notificationsystem.model.UserPreference;

public class NotificationDispatcher {
    private final UserPreferenceService userPreferenceService;

    //constructor initialization
    public  NotificationDispatcher(UserPreferenceService userPreferenceService) {
        this.userPreferenceService = userPreferenceService;
    }

    public void dispatch(Notification notification) {
        UserPreference userPreference = userPreferenceService.getUserPreference(notification.getUserid());

        for (ChannelType channelType : userPreference.getPreference()) {
            NotificationChannel notificationChannel = NotificationChannelFactory.getNotificationChannel(channelType);
            notificationChannel.sendNotification(notification);
        }
    }
}
