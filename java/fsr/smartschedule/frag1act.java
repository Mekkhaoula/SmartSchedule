package fsr.smartschedule;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fsr.smartschedule.UTILE.Utile;

/**
 * Created by ENVY on 09/05/2015.
 */
public class frag1act extends Fragment {
static TextView user;
    @Override

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag1act, container, false);
        user=(TextView)view.findViewById(R.id.textView35);
        user.setText(Utile.username);


        return view;

    }


}
