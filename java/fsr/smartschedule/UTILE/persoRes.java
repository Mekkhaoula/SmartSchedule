package fsr.smartschedule.UTILE;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import fsr.smartschedule.CLASS.Reservation;
import fsr.smartschedule.R;

/**
 * Created by ENVY on 14/05/2015.
 */
public class persoRes extends ArrayAdapter<Reservation> {
    public persoRes(Context context, int resource, Reservation[] objects) {
        super(context, R.layout.persores, objects);}

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater i=LayoutInflater.from(getContext());
        final View c=i.inflate(R.layout.persores,parent,false);
       // TextView heureDebut=(TextView)c.findViewById(R.id.heure);
        ImageView image=(ImageView)c.findViewById(R.id.imageView15);
        image.setBackgroundResource(R.drawable.palii);
       // TextView heureFin=(TextView)c.findViewById(R.id.desc);
        TextView date=(TextView)c.findViewById(R.id.date);
        TextView salle=(TextView)c.findViewById(R.id.salle);
        TextView location=(TextView)c.findViewById(R.id.location);
        TextView creneau=(TextView)c.findViewById(R.id.crenaux);
        TextView user=(TextView)c.findViewById(R.id.user);
       // LinearLayout tswira=(LinearLayout)c.findViewById(R.id.tswira);

    creneau.setText(getItem(position).getHeure());
       // heureDebut.setText(getItem(position).getHeure());
        //heureFin.setText(getItem(position).getV());
        date.setText(getItem(position).getDay());
        salle.setText(getItem(position).getSa().get_name());
        location.setText(getItem(position).getSa().get_location());
        user.setText(getItem(position).getUser());
        if(getItem(position).getV().equals("nv")){
            TextView v=(TextView)c.findViewById(R.id.a);
            v.setBackgroundColor(Color.parseColor("#00baba"));
        }
if(getItem(position).getV().equals("v")) image.setBackgroundResource(R.drawable.pali);
        return  c;
    }


}