package fsr.smartschedule.CLASS;

/**
 * Created by ENVY on 10/05/2015.
 */
public class User {
    private String _email;
    private String _mdp;
    private String _name;
    private String _dept;
    private String _valid;

    public User(){}

    public String get_valid() {
        return _valid;
    }

    public User(String _email, String _valid, String _dept, String _mdp, String _name) {
        this._email = _email;
        this._valid = _valid;
        this._dept = _dept;

        this._mdp = _mdp;
        this._name = _name;
    }

    public String get_email() {
        return _email;
    }

    public String get_mdp() {
        return _mdp;
    }

    public String get_name() {
        return _name;
    }

    public String get_dept() {
        return _dept;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public void set_dept(String _dept) {
        this._dept = _dept;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_mdp(String _mdp) {
        this._mdp = _mdp;
    }

    public boolean equals(User u){if(u.get_email().equals(get_email()) && u.get_mdp().equals(get_mdp())) return true;
        return false;
    }

}