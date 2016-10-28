package fsr.smartschedule.UTILE;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fsr.smartschedule.BD.ASBD;
import fsr.smartschedule.CLASS.AS;
import fsr.smartschedule.R;

public class Favoris extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);
        ListView fav=(ListView)findViewById(R.id.ListFavoris);
        ASBD bd=new ASBD(this,null,null,0);
        fav.setAdapter(new persoSalle(this,0,bd.getAll().toArray()));
       fav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               final AS aa =(AS)parent.getItemAtPosition(position);
               Intent intent=new Intent(parent.getContext(),FavorisReserver.class);
               intent.putExtra("salle", aa.get_name());
               intent.putExtra("location",aa.get_location());
               startActivity(intent);
           }
       });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favoris, menu);
        return true;
    }
public void backFav(View v){onBackPressed();}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
