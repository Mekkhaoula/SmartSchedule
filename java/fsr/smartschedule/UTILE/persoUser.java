package fsr.smartschedule.UTILE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import fsr.smartschedule.CLASS.Reservation;
import fsr.smartschedule.CLASS.User;
import fsr.smartschedule.R;

/**
 * Created by ENVY on 24/05/2015.
 */
public class persoUser extends ArrayAdapter<User> {
    public persoUser(Context context, int resource, User[] objects) {
        super(context, R.layout.persouser, objects);}

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater i=LayoutInflater.from(getContext());
        final View c=i.inflate(R.layout.persouser,parent,false);
        TextView nom=(TextView)c.findViewById(R.id.name);
        TextView email=(TextView)c.findViewById(R.id.email);
        TextView mdp=(TextView)c.findViewById(R.id.mdp);
        TextView dpt=(TextView)c.findViewById(R.id.dept);
        //TextView v=(TextView)c.findViewById(R.id.valid);
        ImageView image=(ImageView)c.findViewById(R.id.imageView17);


        nom.setText(getItem(position).get_name());
        email.setText(getItem(position).get_email());
        mdp.setText(getItem(position).get_mdp());
        dpt.setText(getItem(position).get_dept());
       //v.setText(getItem(position).get_valid());
        if(getItem(position).get_valid().equals("v")) image.setBackgroundResource(R.drawable.pali);


        return  c;
    }


}