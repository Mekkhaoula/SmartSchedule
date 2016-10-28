package fsr.smartschedule.CLASS;

/**
 * Created by ENVY on 09/06/2015.
 */
public class Notification {
    String id;
    String date;
    String vue;
    String message;
    String txt;
    String user;

    public Notification(String user, String message, String date, String vue, String txt, String id) {

        this.user = user;
        this.message = message;
        this.date = date;

        this.vue = vue;
        this.txt = txt;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getTxt() {
        return txt;
    }

    public String getMessage() {
        return message;
    }

    public String getVue() {
        return vue;
    }

    public String getDate() {
        return date;
    }
}
