package fsr.smartschedule.Notification;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.concurrent.ExecutionException;

import fsr.smartschedule.CLASS.Notification;
import fsr.smartschedule.R;
import fsr.smartschedule.UTILE.Utile;
import fsr.smartschedule.UTILE.persoNotif;
import fsr.smartschedule.UTILE.persoUser;

public class NotificationActivite extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_activite);
        final ListView notif=(ListView)findViewById(R.id.notif);
        Notification[] n=new Notification[1];
        AsyncTaskNotif nn=new AsyncTaskNotif();
        try {
            n=nn.execute("user="+ Utile.email).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        notif.setAdapter(new persoNotif(getApplicationContext(), android.R.layout.simple_list_item_1,n));
        notif.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(parent.getContext());
                TextView dec=new TextView(parent.getContext());
                LinearLayout lay=new LinearLayout(parent.getContext());
                builder1.setView(lay);
                lay.setOrientation(LinearLayout.VERTICAL);
                lay.addView(dec);
                final Notification not=(Notification)notif.getItemAtPosition(position);
                dec.setText(not.getMessage());

                builder1.setNeutralButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AsyncTaskValid valid=new AsyncTaskValid();
                        valid.execute("email="+not.getUser()+"&id="+not.getId(),"http://192.168.43.105/pfe/notif_valid.php");
                        Notification[] n=new Notification[1];
                        AsyncTaskNotif nn=new AsyncTaskNotif();
                        try {
                            n=nn.execute("user="+ Utile.email).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                        notif.setAdapter(new persoNotif(getApplicationContext(), android.R.layout.simple_list_item_1,n));
            }
        });
        builder1.show();
    }});}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notification_activite, menu);
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
    private static class AsyncTaskNotif extends AsyncTask<String, String, Notification[]> {



        @Override
        protected Notification[] doInBackground(String... params) {

            // URL url = null;
            String result = "",r="";Notification[] salles=null;
            try {



                URL url = new URL("http://192.168.43.105/pfe/select_notif.php");
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


                BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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
                salles=new Notification[json.length()];int j=0;
                for(int i=0;i<json.length();i++) {

                    JSONObject jsonn = (JSONObject) json.get(i);
                    String user = (String) jsonn.get("user");
                    String message = (String) jsonn.get("message");
                    String date = (String) jsonn.get("date");
                    String vue = (String) jsonn.get("vue");
                    String txt = (String) jsonn.get("txt");
                    String id = (String) jsonn.get("id");

                   salles[i]=new Notification(user,message,date,vue,txt,id);


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
    private static class AsyncTaskValid extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(String... params) {

            URL url = null;String result = "";
            try {

                url = new URL(params[1]);


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
