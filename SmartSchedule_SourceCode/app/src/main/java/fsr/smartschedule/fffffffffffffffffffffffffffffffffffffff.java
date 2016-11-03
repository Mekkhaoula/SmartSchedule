package fsr.smartschedule;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.JsonWriter;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.json.*;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import fsr.smartschedule.BD.ASBD;
import fsr.smartschedule.BD.ReservationBD;
import fsr.smartschedule.BD.UserBD;
import fsr.smartschedule.CLASS.AS;
import fsr.smartschedule.CLASS.Reservation;
import fsr.smartschedule.CLASS.User;


public class fffffffffffffffffffffffffffffffffffffff extends ActionBarActivity {
String s="http://192.168.43.105/pfe/select_Sa.php";
    ASBD BD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fffffffffffffffffffffffffffffffffffffff);
        UserBD u=new UserBD(this,null,null,0);
        u.deleteAll();
        final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        LinearLayout lay = new LinearLayout(getApplicationContext());
        builder1.setView(lay);


      //  u.addUser(new User("a","a","a","a"));
       // u.addUser(new User("b","b","b","b"));

       // AsyncTaskRunner r=new AsyncTaskRunner();
        TextView t;
        t=(TextView)findViewById(R.id.textView18);
        WebView w=(WebView)findViewById(R.id.webView); w.getSettings().setJavaScriptEnabled(true);
        w.loadUrl("http://192.168.43.105/pfe/i.php");
        WebView v = (WebView) findViewById(R.id.webView);
        v.setVerticalScrollBarEnabled(true);
        v.setHorizontalScrollBarEnabled(true);

        builder1.show();
        ;
         BD=new ASBD(this,null,null,0);
      /*  try {
           /* t.setText(r.execute(s).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            }*/Date date1=null;
      /*  ReservationBD r=new ReservationBD(this,null,null,0);
        r.addReservation(new Reservation("String user", "String day", "String time", new AS("e","e"), "String v", "String timeR"));

t.setText(r.databaseToString());*/
        AsyncTaskSelect r=new AsyncTaskSelect();

        try {
            String a=r.execute().get();
          //  t.setText(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//t.setText(BD.databaseToString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fffffffffffffffffffffffffffffffffffffff, menu);
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
   private class AsyncTaskRunner extends AsyncTask<String, String, AS[]> {



        @Override
        protected AS[] doInBackground(String... params) {

           // URL url = null;
            String result = "",r="";AS[] salles=null;
            try {


// send as http get request
                URL url = new URL("http://192.168.43.105/pfe/select_Sa.php");
                URLConnection conn = url.openConnection();

// Get the response
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
            try {
                JSONArray json = new JSONArray(result);
                salles=new AS[json.length()];
                for(int i=0;i<json.length();i++) {

                    JSONObject jsonn = (JSONObject) json.get(i);
                    String nom = (String) jsonn.get("nom");
                    String location = (String) jsonn.get("location");
                    salles[i]=new AS(nom,location);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return salles;
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

    private class AsyncTaskSelect extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(String... params) {

            URL url = null;String result = "";
            try {

                url = new URL("http://192.168.43.105/pfe/select_res_id.php");




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
       String data="2015-4-1910:00-12:00AmphieACentrale";
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
