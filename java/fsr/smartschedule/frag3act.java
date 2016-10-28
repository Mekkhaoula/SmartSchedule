package fsr.smartschedule;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import fsr.smartschedule.BD.ASBD;
import fsr.smartschedule.UTILE.Utile;

/**
 * Created by ENVY on 09/05/2015.
 */
public class frag3act extends Fragment {

    @Override

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag1act, container, false);ASBD BD=new ASBD(view.getContext(),null,null,0);
       // ListView salle=(ListView)view.findViewById(R.id.salle);
       // salle.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, BD.getAll().string()));

        TextView username=(TextView)view.findViewById(R.id.textView35);
        username.setText(Utile.username);

        return view;

    }
}