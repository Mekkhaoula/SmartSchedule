package fsr.smartschedule.UTILE;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fsr.smartschedule.BD.ASBD;
import fsr.smartschedule.Home;
import fsr.smartschedule.R;

/**
 * Created by ENVY on 05/06/2015.
 */
public class fragAdmin extends Fragment {
    @Override

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragadmin, container, false);
        // ListView salle=(ListView)view.findViewById(R.id.salle);
        // salle.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, BD.getAll().string()));

        TextView username=(TextView)view.findViewById(R.id.textView35);
        username.setText(Utile.username);

        return view;

    }
}
