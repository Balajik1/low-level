package org.example;

import notificationsystem.api.NotificationService;
import notificationsystem.model.ChannelType;
import notificationsystem.model.Notification;
import notificationsystem.model.UserPreference;
import notificationsystem.service.NotificationDispatcher;
import notificationsystem.service.UserPreferenceService;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //defining preference service
        UserPreferenceService preferenceService= new UserPreferenceService();

        //defining user preference with EMAIL and SMS as preferred channels
        UserPreference userPreference = new UserPreference("user1", Set.of(ChannelType.EMAIL, ChannelType.SMS));
        preferenceService.saveUserPreference(userPreference);

        //defining notification dispatcher
        NotificationDispatcher notificationDispatcher = new NotificationDispatcher(preferenceService);

        //defining synchronous send notification service
        NotificationService notificationService = new NotificationService(notificationDispatcher);

        //sending notification
        notificationService.sendNotification(new Notification("user1", "Your order has been shipped!"));

    }
}