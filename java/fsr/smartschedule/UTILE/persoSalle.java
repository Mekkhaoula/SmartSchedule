package fsr.smartschedule.UTILE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import fsr.smartschedule.CLASS.AS;
import fsr.smartschedule.CLASS.User;
import fsr.smartschedule.R;

/**
 * Created by ENVY on 05/06/2015.
 */
public class persoSalle extends ArrayAdapter<AS> {
    public persoSalle(Context context, int resource, AS[] objects) {
        super(context, R.layout.persosalle, objects);}

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater i=LayoutInflater.from(getContext());
        final View c=i.inflate(R.layout.persosalle,parent,false);
        TextView nom=(TextView)c.findViewById(R.id.nomSalle);
        TextView loc=(TextView)c.findViewById(R.id.location);

        nom.setText(getItem(position).get_name());
        loc.setText(getItem(position).get_location());






        return  c;
    }
}
