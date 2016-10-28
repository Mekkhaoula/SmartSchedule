package fsr.smartschedule.RESERVATION;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import fsr.smartschedule.BD.ASBD;
import fsr.smartschedule.CLASS.AS;
import fsr.smartschedule.R;
import fsr.smartschedule.UTILE.persoRes;
import fsr.smartschedule.UTILE.pesores2;

public class Recherche extends ActionBarActivity {
ListView salleList;
    String s;
    String sa;
    ImageView fav;
    WebView w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);
        salleList=(ListView)findViewById(R.id.saale2);
         w=(WebView)findViewById(R.id.webView2); w.getSettings().setJavaScriptEnabled(true);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
        AsyncTaskSalle a=new AsyncTaskSalle();
        fav=(ImageView)findViewById(R.id.imageView12);
        String[] salles={""};
        s="Annexe 1";
        sa="Salle 1";
        try {
             String[] salles1=a.execute("Annexe 1").get();
            if(salles1!=null) salles=salles1;w.loadUrl("http://192.168.43.105/pfe/i.php?location="+s+"&salle="+sa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
ArrayAdapter adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, salles);
        salleList.setAdapter(adapter);
        salleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sa=parent.getItemAtPosition(position).toString();
                favIcon();
                w.loadUrl("http://192.168.43.105/pfe/i.php?location="+s+"&salle="+sa);
            }
        });

        w.loadUrl("http://192.168.43.105/pfe/i.php?location="+s+"&salle="+sa);
        WebView v = (WebView) findViewById(R.id.webView2);
        v.setVerticalScrollBarEnabled(true);
        v.setHorizontalScrollBarEnabled(true);
    }

    public void favIcon(){
        fav.setBackgroundResource(R.drawable.tr);
        ASBD asbd=new ASBD(this,null,null,0);
        AS as=asbd.getAS(s+sa);
        if(!as.get_location().equals("null") || !as.get_name().equals("null")){
            fav.setBackgroundResource(R.drawable.faav);
        }

    }
    protected void onPause()
    {
        super.onPause();
        //closing transition animations
        overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
    }
    public void annex1(View v){
        String[] salles={""};
        try {
            s="Annexe 1";
            AsyncTaskSalle a=new AsyncTaskSalle();
            String[] salles1=a.execute("Annexe 1").get();
            if(salles1!=null) salles=salles1;
            w.loadUrl("http://192.168.43.105/pfe/i.php?location="+s+"&salle="+sa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        salleList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, salles));
        favIcon();
    }

    public void annex2(View v){
        String[] salles={""};
        try {s="Annexe 2";
            AsyncTaskSalle a=new AsyncTaskSalle();
            String[] salles1=a.execute("Annexe 2").get();
            if(salles1!=null) salles=salles1;w.loadUrl("http://192.168.43.105/pfe/i.php?location="+s+"&salle="+sa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        salleList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, salles));
        favIcon();
    }

    public void centrale(View v){
        String[] salles={""};
        try {s="Centrale";
            AsyncTaskSalle a=new AsyncTaskSalle();
            String[] salles1=a.execute("Centrale").get();
            if(salles1!=null) salles=salles1;w.loadUrl("http://192.168.43.105/pfe/i.php?location="+s+"&salle="+sa);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        salleList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, salles));
        favIcon();
    }
    public void addToFav(View v){
        ASBD asbd=new ASBD(this,null,null,0);
        AS as=asbd.getAS(s+sa);
        if(as.get_location().equals("null") || as.get_name().equals("null")){
            asbd.addAS(new AS(sa,s));
        }
        else{
            asbd.delete(new AS(sa,s));
        }



        favIcon();

    }
    public void voirDispo(View v){
        final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        LinearLayout lay = new LinearLayout(getApplicationContext());
        builder1.setView(lay);

        final Spinner days=new Spinner(this);
        lay.addView(days);
        final String[] a= new String[]{
                "8:00-10:00",

                "10:00-12:00",

                "12:00-14:00",

                "14:00-16:00",

                "16:00-18:00",};
        Calendar ccc = Calendar.getInstance();Date tomorrow = ccc.getTime();
        final String zz[]=new String[356];
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-M-d");
        for(int i=0;i<355;i++) {
            ccc.add(Calendar.DAY_OF_YEAR, 1);
            tomorrow = ccc.getTime();
            zz[i]=String.format(formater.format(tomorrow));
        }

            final ListView dispo=new ListView(this);
            lay.addView(dispo);
        lay.setOrientation(LinearLayout.VERTICAL);

        days.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, zz));


           days.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                   String[] ss=new String[]{days.getSelectedItem().toString()+a[0]+sa+s,
                           days.getSelectedItem().toString()+a[1]+sa+s,
                           days.getSelectedItem().toString()+a[2]+sa+s,
                           days.getSelectedItem().toString()+a[3]+sa+s,
                           days.getSelectedItem().toString()+a[4]+sa+s,
                          };

                   dispo.setAdapter(new pesores2(getApplicationContext(), android.R.layout.simple_list_item_1,
                           ss));



               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {

               }
           });


        lay.setBackgroundResource(R.drawable.ffff);
        //in my example i use TYPE_CLASS_NUMBER for input only numbers

       lay.setAlpha(0.85F);

        builder1.show();
    }
    public void backRecherche(View v){onBackPressed();}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recherche, menu);
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

    private class AsyncTaskSalle extends AsyncTask<String, String, String[]> {



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
