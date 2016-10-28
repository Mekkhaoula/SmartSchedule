package fsr.smartschedule.RESERVATION;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import fsr.smartschedule.CLASS.AS;
import fsr.smartschedule.CLASS.Reservation;
import fsr.smartschedule.R;
import fsr.smartschedule.UTILE.persoRes;

public class DoReservation extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_reservation);
        final Spinner location =(Spinner)findViewById(R.id.Location);
        location.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"Annexe1","Annexe2","Centrale"}));
        final Spinner salle =(Spinner)findViewById(R.id.SpinnerSalle);
         AsyncTaskRunner r=new AsyncTaskRunner();
      /*  try {
            String[] tabdesalle=r.execute("Annexe1").get(); salle.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tabdesalle ));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
final ListView reserver =(ListView)findViewById(R.id.listView3);
        reserver.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new String[]{"8:00---->18:00"}));


location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String l=(String) location.getItemAtPosition(position);String[] s;String[] tabdesalle={};
        AsyncTask a=new AsyncTaskRunner();
        try { AsyncTaskRunner r=new AsyncTaskRunner();
            tabdesalle=r.execute(l).get();



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        salle.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,tabdesalle ));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});


    }
    public void showCalendar(View v){
        DrawerLayout d=(DrawerLayout)findViewById(R.id.drawer_layout);
        d.openDrawer(Gravity.LEFT);
    }
    public void showCalendar(){
        DrawerLayout d=(DrawerLayout)findViewById(R.id.drawer_layoutt);
        d.openDrawer(Gravity.LEFT);
    }
    public void hidecalendar(){
        DrawerLayout d=(DrawerLayout)findViewById(R.id.drawer_layoutt);
        d.closeDrawer(Gravity.LEFT);
    }
    public void date(View v){
        Button b;
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int  mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);

    showCalendar();
         CalendarView calendarView = (CalendarView) findViewById(
                R.id.calendar_view);
        calendarView.setOnDateChangeListener(new
                                                     CalendarView.OnDateChangeListener() {
                                                         @Override
                                                         public void onSelectedDayChange(CalendarView view,
                                                                                         int year, int month, int dayOfMonth) {

                                                             TextView date=(TextView)findViewById(R.id.textView24);
                                                             date.setText(String.format(dayOfMonth+"-"+month+"-"+year));
                                                             hidecalendar();
                                                         }
                                                     });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_do_reservation, menu);
        return true;
    }

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

        return super.onOptionsItemSelected(item);}

        private class AsyncTaskRunner extends AsyncTask<String, String, String[]> {



            @Override
            protected String[] doInBackground(String... params) {

                // URL url = null;
                String result = "",r="";String[] salles=null;
                try {



                    URL url = new URL("http://192.168.43.105/pfe/select_Sa.php");
                    URLConnection conn = url.openConnection();


                    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;
                    while ((line = rd.readLine()) != null) {
                        result+=line+"/";
                        r=result;

                    }
                    rd.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String[] salles2={};
                try {
                    JSONArray json = new JSONArray(result);
                    salles=new String[json.length()];int j=0;
                    for(int i=0;i<json.length();i++) {

                        JSONObject jsonn = (JSONObject) json.get(i);
                        String nom = (String) jsonn.get("nom");
                        String location = (String) jsonn.get("location");
                        if(location.equals((String)params[0])){
                        salles[j]=nom;j++;}
                         salles2=new String[j];
                        for(int ii=0;ii<j;ii++)
                            salles2[ii]=salles[ii];

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                return salles2;
            }


            /**
             *
             * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
             */
            // @Override
            protected void onPostExecute(String result) {
                // textView.setText(result);
                // execution of result of Long time consuming operation
                // In this example it is the return value from the web service
                // textView.setText(result);
                // dialog.dismiss();

            }

            /**
             *
             * @see android.os.AsyncTask#onPreExecute()
             */
            @Override
            protected void onPreExecute() {
                // Things to be done before execution of long running operation. For
                // example showing ProgessDialog
                // dialog = ProgressDialog.show(MainActivity.this, "Loading", "Loading data, please wait..");

            }


            @Override
            protected void onProgressUpdate(String... text) {
                // textView.setText(text[0]);
                // Things to be done while execution of long running operation is in
                // progress. For example updating ProgessDialog
            }
        }



}
