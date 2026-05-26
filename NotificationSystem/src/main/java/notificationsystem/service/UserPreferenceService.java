package notificationsystem.service;

import notificationsystem.model.ChannelType;
import notificationsystem.model.UserPreference;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserPreferenceService {
    Map<String, UserPreference> userPreferenceMap = new ConcurrentHashMap<>();

    public void saveUserPreference(UserPreference userPreference) {
        userPreferenceMap.put(userPreference.getUserid(), userPreference);
    }

    public UserPreference getUserPreference(String userid) {
        return userPreferenceMap.getOrDefault(userid,new UserPreference(userid, Set.of(ChannelType.SMS)));
    }
}
