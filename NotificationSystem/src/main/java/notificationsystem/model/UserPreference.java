package notificationsystem.model;

import java.util.Set;

public class UserPreference {
    String userid;
    Set<ChannelType> preference;
    public UserPreference(String userid, Set<ChannelType> preference) {
        this.userid = userid;
        this.preference = preference;
    }
    public String getUserid() {
        return userid;
    }

    //TODO, this method is need to be removed
    public void changePreference(Set<ChannelType> preference) {
        this.preference = preference;
    }

    public Set<ChannelType> getPreference() {
            return preference;
    }
}
