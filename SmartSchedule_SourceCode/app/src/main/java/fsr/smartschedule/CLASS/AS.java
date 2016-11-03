package fsr.smartschedule.CLASS;

/**
 * Created by ENVY on 10/05/2015.
 */
public class AS {
    private String _id;
    private String _name;
    private String _location;

    public AS(String _name, String _location) {
        this._name = _name;
        this._location = _location;
        this._id=String.format(_location+_name);}

    public String get_id() {
        return _id;
    }

    public String get_location() {
        return _location;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_location(String _location) {
        this._location = _location;
    }

    public String get_name() {
        return _name;

    }
}
