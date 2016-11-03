package fsr.smartschedule.UTILE;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import fsr.smartschedule.CLASS.Reservation;
import fsr.smartschedule.R;

/**
 * Created by ENVY on 29/05/2015.
 */
public class pesores2 extends ArrayAdapter<String> {
    TextView dispo;
    public pesores2(Context context, int resource, String[] objects) {
        super(context, R.layout.persores, objects);}

    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }
    public String getItemm(int position) {
        return dispo.getText().toString();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater i=LayoutInflater.from(getContext());
        final View c=i.inflate(R.layout.persores2,parent,false);

        String[] times =new String[]{
                "8:00-10:00",

                "10:00-12:00",

                "12:00-14:00",

                "14:00-16:00",

                "16:00-18:00"};

        TextView azer=(TextView)c.findViewById(R.id.azer);
        azer.setText(times[position]);
         dispo=(TextView)c.findViewById(R.id.dispo);
        AsyncTaskSelect s=new AsyncTaskSelect();
        try {
            dispo.setText(s.execute("id="+getItem(position)).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        dispo.setTextColor((Color.parseColor("#ffffff")));
        azer.setTextColor((Color.parseColor("#00baba")));

        return  c;
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
                String data=params[0];
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