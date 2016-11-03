package fsr.smartschedule.CLASS;

/**
 * Created by ENVY on 10/05/2015.
 */
public class Reservation {
    private String id;
    private String user;
    private String day;
    private String heure;
    private String desc;
    private AS sa;
    private String v ;
    private String timeR;

    public Reservation(String id,String user, String day, String heure, String desc, AS sa, String v, String timeR) {
        this.user = user;
        this.day = day;
        this.heure = heure;
        this.desc = desc;
        this.sa = sa;
        this.v = v;
        this.timeR = timeR;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getDay() {
        return day;
    }

    public String getHeure() {
        return heure;
    }

    public String getDesc() {
        return desc;
    }

    public AS getSa() {
        return sa;
    }

    public String getV() {
        return v;
    }

    public String getTimeR() {
        return timeR;
    }
}
