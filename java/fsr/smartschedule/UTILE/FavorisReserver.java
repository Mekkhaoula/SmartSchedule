package fsr.smartschedule.UTILE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import fsr.smartschedule.CLASS.AS;
import fsr.smartschedule.R;

public class FavorisReserver extends ActionBarActivity {
    String salle;
    String location;
    String date;
    CalendarView cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris_reserver);
        Intent intent = getIntent();
        cal=(CalendarView)findViewById(R.id.calendar_view);

        salle=intent.getStringExtra("salle");
        location=intent.getStringExtra("location");
TextView textSalle=(TextView)findViewById(R.id.textSalle);
        textSalle.setText(salle+"/"+location);






        final String[] a= new String[]{
                "8:00-10:00",

                "10:00-12:00",

                "12:00-14:00",

                "14:00-16:00",

                "16:00-18:00",};



        final ListView dispo=(ListView)findViewById(R.id.listView2);





        cal.setOnDateChangeListener(new
                                                     CalendarView.OnDateChangeListener() {
                                                         @Override
                                                        public void onSelectedDayChange(CalendarView view,
                                                                                         final int year, final int month, final int dayOfMonth) {


        date=String.format(year + "-" + month + "-" + dayOfMonth);
        String[] ss=new String[]{date+a[0]+salle+location,
                date+a[1]+salle+location,
                date+a[2]+salle+location,
                date+a[3]+salle+location,
                date+a[4]+salle+location};
        dispo.setAdapter(new pesores2(getApplicationContext(), android.R.layout.simple_list_item_1,
                ss));
                            }
});
        dispo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(parent.getContext());
                TextView dec = new TextView(parent.getContext());
                LinearLayout lay = new LinearLayout(parent.getContext());
                builder1.setView(lay);
                lay.setOrientation(LinearLayout.VERTICAL);
                lay.addView(dec);
                final EditText desc = new EditText(parent.getContext());
                dec.setText("decriver votre reservation");
                lay.addView(desc);
                builder1.setNeutralButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String data = "id=" + date + a[position].toString() + salle + location
                                + "&user=" + Utile.email
                                + "&day=" + date
                                + "&heure=" + a[position].toString()
                                + "&desc=" + desc.getText().toString()
                                + "&as=" + salle
                                + "&v=nv"
                                + "&timer=timer"
                                + "&locat=" + location;


                        AsyncTaskRes n = new AsyncTaskRes();
                        n.execute(data);
                        String[] ss=new String[]{date+a[0]+salle+location,
                                date+a[1]+salle+location,
                                date+a[2]+salle+location,
                                date+a[3]+salle+location,
                                date+a[4]+salle+location};
                        dispo.setAdapter(new pesores2(getApplicationContext(), android.R.layout.simple_list_item_1,
                                ss));
                    }
                });
                builder1.show();

    }});}




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favoris_reserver, menu);
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

        return super.onOptionsItemSelected(item);
    }
    private static class AsyncTaskRes extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(String... params) {

            URL url = null;String result = "";
            try {

                url = new URL("http://192.168.43.105/pfe/insert_resa.php");


                String data = params[0];



                HttpURLConnection connection = (HttpURLConnection) url.openConnection();



                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(false);
                connection.setRequestMethod("POST");

                //  connection.setRequestProperty("Content-Type",
                //  "application/x-www-form-urlencoded");

                // Send the POST data
                DataOutputStream dataOut = new DataOutputStream(
                        connection.getOutputStream());

                dataOut.writeBytes(data);

                dataOut.flush();
                dataOut.close();

                DataInputStream in = new DataInputStream(connection.getInputStream());

                String g;
                while ((g = in.readLine()) != null) {
                    result += g;
                }
                in.close();connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            TextView t;



            return result;
        }


        /**
         *
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
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
