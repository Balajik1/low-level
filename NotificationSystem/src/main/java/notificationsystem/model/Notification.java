package notificationsystem.model;

public class Notification {
    private final String userid;
    private final String message;
    public Notification(String userid, String message) {
        this.userid = userid;
        this.message = message;
    }

    public String getUserid() {
        return userid;
    }
    public String getMessage() {
        return message;
    }
}
