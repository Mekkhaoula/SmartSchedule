package fsr.smartschedule.UTILE;

/**
 * Created by ENVY on 09/06/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import fsr.smartschedule.CLASS.Notification;
import fsr.smartschedule.CLASS.User;
import fsr.smartschedule.R;



/**
 * Created by ENVY on 24/05/2015.
 */
public class persoNotif extends ArrayAdapter<Notification> {
    public persoNotif(Context context, int resource, Notification[] objects) {
        super(context, R.layout.personotif, objects);}

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater i=LayoutInflater.from(getContext());
        final View c=i.inflate(R.layout.personotif,parent,false);
        TextView message=(TextView)c.findViewById(R.id.messageeee);
        TextView date=(TextView)c.findViewById(R.id.date);


        message.setText(getItem(position).getTxt());
        date.setText(getItem(position).getDate());
        if(getItem(position).getVue().equals("false")){
            TextView vu=(TextView)c.findViewById(R.id.vue);
            vu.setText("");
        }





        return  c;
    }


}