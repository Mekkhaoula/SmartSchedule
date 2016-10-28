package fsr.smartschedule.RESERVATION;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fsr.smartschedule.R;
import fsr.smartschedule.UTILE.Utile;
import fsr.smartschedule.UTILE.pesores2;

public class FaireRes extends ActionBarActivity {
    static Spinner salle;
    static Spinner loc;
    static Button ddate;
     static Spinner salllle;
     static ListView l;
    static String[] tabdesalle={};
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faire_res);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
//CalendarView calendar=(CalendarView)findViewById(R.id.calendar_view);
      // calendar.setShownWeekCount(1);
ddate=(Button)findViewById(R.id.ddate);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
             loc =(Spinner)findViewById(R.id.loc);

        AsyncTaskRunner as=new AsyncTaskRunner();
         String[] loca=null;
        try {

           loca=as.execute().get();
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        loc.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,loca));
        loc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] a= new String[]{
                        "8:00-10:00",

                        "10:00-12:00",

                        "12:00-14:00",

                        "14:00-16:00",

                        "16:00-18:00",};

                String ll=(String) loc.getItemAtPosition(0);

                AsyncTaskSalle r=new AsyncTaskSalle();
                try {
                    tabdesalle=r.execute(ll).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                String[] ss=new String[]{ddate.getText().toString()+a[0].toString()+tabdesalle[0]+loc.getSelectedItem().toString(),
                        ddate.getText().toString()+a[1].toString()+tabdesalle[0]+loc.getSelectedItem().toString(),
                        ddate.getText().toString()+a[2].toString()+tabdesalle[0]+loc.getSelectedItem().toString(),
                        ddate.getText().toString()+a[3].toString()+tabdesalle[0]+loc.getSelectedItem().toString(),
                        ddate.getText().toString()+a[4].toString()+tabdesalle[0]+loc.getSelectedItem().toString()};
                l=(ListView)findViewById(R.id.listcreneaux);
                l.setAdapter(new pesores2(getApplicationContext(), android.R.layout.simple_list_item_1,
                        ss));
                String l=(String) loc.getItemAtPosition(position);String[] s;

                try { AsyncTaskSalle er=new AsyncTaskSalle();
                    tabdesalle=er.execute(l).get();



                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                salle=(Spinner)findViewById(R.id.spinnersalle);
                salle.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,tabdesalle ));



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }});
     //  salle=(Spinner)findViewById(R.id.spinnersalle);
        }



    protected void onPause()
    {
        super.onPause();
        //closing transition animations
        overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
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

    public void res(View v){AsyncTaskRes r=new AsyncTaskRes();
    r.execute();}
    public void date(final View v){
        Button  b;
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int  mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);

        showCalendar();
        CalendarView calendarView = (CalendarView) findViewById(
                R.id.calendar_view);

        final Button finalB =b=(Button)v;
        calendarView.setOnDateChangeListener(new
                                                     CalendarView.OnDateChangeListener() {
                                                         @Override
                                                         public void onSelectedDayChange(CalendarView view,
                                                                                         final int year, final int month, final int dayOfMonth) {

                                                             TextView date=(TextView)findViewById(R.id.textView24);
                                                            // date.setText(String.format(dayOfMonth+"-"+month+"-"+year));
                                                             //hidecalendar();

                                                             finalB.setText(String.format(year + "-" + month + "-" + dayOfMonth));



                                                                     ListView sal=(ListView)findViewById(R.id.listsalle);
                                                                     AsyncTaskSallen s=new AsyncTaskSallen();

                                                             String[] a= new String[]{
                                                                     "8:00-10:00",

                                                                     "10:00-12:00",

                                                                     "12:00-14:00",

                                                                     "14:00-16:00",

                                                                     "16:00-18:00",};

                                                             String ll=(String) loc.getItemAtPosition(0);

                                                             AsyncTaskSalle r=new AsyncTaskSalle();
                                                             try {
                                                                 tabdesalle=r.execute(ll).get();
                                                             } catch (InterruptedException e) {
                                                                 e.printStackTrace();
                                                             } catch (ExecutionException e) {
                                                                 e.printStackTrace();
                                                             }
                                                             String[] ss=new String[]{ddate.getText().toString()+a[0].toString()+tabdesalle[0]+loc.getSelectedItem().toString(),
                                                                     ddate.getText().toString()+a[1].toString()+tabdesalle[0]+loc.getSelectedItem().toString(),
                                                                     ddate.getText().toString()+a[2].toString()+tabdesalle[0]+loc.getSelectedItem().toString(),
                                                                     ddate.getText().toString()+a[3].toString()+tabdesalle[0]+loc.getSelectedItem().toString(),
                                                                     ddate.getText().toString()+a[4].toString()+tabdesalle[0]+loc.getSelectedItem().toString()};
                                                             l=(ListView)findViewById(R.id.listcreneaux);
                                                             l.setAdapter(new pesores2(getApplicationContext(), android.R.layout.simple_list_item_1,
                                                                     ss));


                                                         }
                                                     });

    }


    public void neutre(){
        FrameLayout a=(FrameLayout)findViewById(R.id.a);
        a.setBackgroundColor(Color.parseColor("#00ff3053"));
        FrameLayout b=(FrameLayout)findViewById(R.id.b);
        b.setBackgroundColor(Color.parseColor("#00ff3053"));

    }

    public void ref(View v) throws ExecutionException, InterruptedException {
//        String id =ddate.getText().toString()+l.getSelectedItem().toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString();
        AsyncTaskSelect s1=new AsyncTaskSelect();
        AsyncTaskSelect s2=new AsyncTaskSelect();
        AsyncTaskSelect s3=new AsyncTaskSelect();
        AsyncTaskSelect s4=new AsyncTaskSelect();
        AsyncTaskSelect s5=new AsyncTaskSelect();
        String time1=s1.execute("id=2015-4-1910:00-12:00AmphieACentrale").get();
        String time2=s2.execute("id="+ddate.getText().toString()+l.getItemAtPosition(1).toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString()).get();
        String time3=s3.execute("id="+ddate.getText().toString()+l.getItemAtPosition(2).toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString()).get();
        String time4=s4.execute("id="+ddate.getText().toString()+l.getItemAtPosition(3).toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString()).get();
        String time5=s5.execute("id="+ddate.getText().toString()+l.getItemAtPosition(4).toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString()).get();
    /* String[] times=new String[5];
        times[0]=time1;
     if(time1.equals("yes")) times[1]="oui";
        if(time2.equals("yes")) times[1]="oui";
        if(time3.equals("yes")) times[2]="oui";
        if(time4.equals("yes")) times[3]="oui";
        if(time5==("yes")) times[4]="oui";
        l.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,times));
*/
    }

    public void a(View v){neutre();
        FrameLayout a=(FrameLayout)findViewById(R.id.a);
        a.setBackgroundColor(Color.parseColor("#00baba"));
        mViewPager.setCurrentItem(0);
    }

    public void b(View v){neutre();

        FrameLayout b=(FrameLayout)findViewById(R.id.b);
        b.setBackgroundColor(Color.parseColor("#00baba"));
        mViewPager.setCurrentItem(1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_faire_res, menu);
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
    public void backFaireRes(View v){onBackPressed();}


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position==0)
            return PlaceholderFragment.newInstance(position + 1);
            else{return ParCreneaux.newInstance(position + 1);}
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_faire_res, container, false);
             l=(ListView)rootView.findViewById(R.id.listcreneaux);
            final pesores2[] p = new pesores2[1];
            l.setAdapter(new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1,new String[]{
                    "8:00-10:00",

                    "10:00-12:00",

                    "12:00-14:00",

                    "14:00-16:00",

                    "16:00-18:00",}));
            l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    final String[] a= new String[]{
                            "8:00-10:00",

                            "10:00-12:00",

                            "12:00-14:00",

                            "14:00-16:00",

                            "16:00-18:00",};
                    //if(!l.getItemm(position).equals("reserve")){


                    String iid=l.getItemAtPosition(position).toString(); String resu="n";
                    AsyncTaskSelectr rr=new AsyncTaskSelectr();
                    try {
                        resu=rr.execute("id="+ddate.getText().toString()+a[position].toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString()).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    if(resu.equals("non reserve")){
                    final AlertDialog.Builder builder1 = new AlertDialog.Builder(parent.getContext());
                    TextView dec=new TextView(parent.getContext());
                    LinearLayout lay=new LinearLayout(parent.getContext());
                    builder1.setView(lay);
                    lay.setOrientation(LinearLayout.VERTICAL);
                    lay.addView(dec);
                    final EditText desc=new EditText(parent.getContext());
                    dec.setText("decriver votre reservation");
                    lay.addView(desc);
                    builder1.setNeutralButton("Valider", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String data = "id="+ddate.getText().toString()+a[position].toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString()
                                    +"&user="+Utile.email
                                    +"&day="+ddate.getText().toString()
                                    +"&heure="+a[position].toString()
                                    +"&desc="+desc.getText().toString()
                                    +"&as="+salle.getSelectedItem().toString()
                                    +"&v=nv"
                                    +"&timer=timer"
                                    +"&locat="+loc.getSelectedItem().toString();


                            AsyncTaskRes n=new AsyncTaskRes();
                            n.execute(data);
                            ListView sal=(ListView)rootView.findViewById(R.id.listsalle);
                            AsyncTaskSallen s=new AsyncTaskSallen();
                            String[] ss=new String[]{ddate.getText().toString()+a[0].toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString(),
                                    ddate.getText().toString()+a[1].toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString(),
                                    ddate.getText().toString()+a[2].toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString(),
                                    ddate.getText().toString()+a[3].toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString(),
                                    ddate.getText().toString()+a[4].toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString()};
p[0] =new pesores2(rootView.getContext(), android.R.layout.simple_list_item_1,
        ss);
                            l.setAdapter(p[0]);
                        }
                    });
builder1.show();}}


            });
           salle=(Spinner)rootView.findViewById(R.id.spinnersalle);
          salle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String[] a= new String[]{
                            "8:00-10:00",

                            "10:00-12:00",

                            "12:00-14:00",

                            "14:00-16:00",

                            "16:00-18:00",};

                    String ll=(String) loc.getSelectedItem();

                    AsyncTaskSalle r=new AsyncTaskSalle();
                    try {
                        tabdesalle=r.execute(ll).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    String[] ss=new String[]{ddate.getText().toString()+a[0].toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString(),
                            ddate.getText().toString()+a[1].toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString(),
                            ddate.getText().toString()+a[2].toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString(),
                            ddate.getText().toString()+a[3].toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString(),
                            ddate.getText().toString()+a[4].toString()+salle.getSelectedItem().toString()+loc.getSelectedItem().toString()};
                    p[0] =new pesores2(rootView.getContext(), android.R.layout.simple_list_item_1,
                            ss);
                    l.setAdapter(p[0]);
                    l.setAdapter(p[0]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            return rootView;
        }
    }

    public static class ParCreneaux extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ParCreneaux newInstance(int sectionNumber) {
            ParCreneaux fragment = new ParCreneaux();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public ParCreneaux() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.parcrenaux, container, false);
             salllle=(Spinner)rootView.findViewById(R.id.spinnercreneaux);
            salllle.setAdapter(new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1,new String[]{
                    "8:00-10:00",

                    "10:00-12:00",

                    "12:00-14:00",

                    "14:00-16:00",

                    "16:00-18:00",}));
            salllle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            final ListView sal=(ListView)rootView.findViewById(R.id.listsalle);

            sal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                    String data = "id="+ddate.getText().toString()+salllle.getItemAtPosition(position).toString()+parent.getItemAtPosition(position)+loc.getSelectedItem().toString()
                            +"&user="+ Utile.email
                            +"&day="+ddate.getText().toString()
                            +"&heure="+l.getItemAtPosition(position).toString()
                            +"&desc=desc"
                            +"&as="+parent.getItemAtPosition(position).toString()
                            +"&v=nv"
                            +"&timer=timer"
                            +"&locat="+loc.getSelectedItem().toString();




                }
            });


            return rootView;
        }
    }
    private class AsyncTaskRunner extends AsyncTask<String, String, String[]> {



        @Override
        protected String[] doInBackground(String... params) {

            // URL url = null;
            String result = "",r="";String[] salles=null;
            try {



                URL url = new URL("http://192.168.43.105/pfe/select_loc.php");
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
                    //String location = (String) jsonn.get("location");

                        salles[j]=nom;j++;
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

    //salle

    private static class AsyncTaskSalle extends AsyncTask<String, String, String[]> {



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

    private static class AsyncTaskSallen extends AsyncTask<String, String, String[]> {



        @Override
        protected String[] doInBackground(String... params) {

            // URL url = null;
            String result = "",r="";String[] salles=new String[]{"dd"};String[] ss=new String[]{""};
            try {



                URL url = new URL("http://192.168.43.105/pfe/select_res_n.php");

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


                DataInputStream rd = new DataInputStream(connection.getInputStream());
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
                    String nom = (String) jsonn.get("as");
                    String location = (String) jsonn.get("location");
     if(location.equals(params[1]))
         { salles[j]=nom;j++;}


                }
                 ss=new String[j];
                for(int i=0;i<j;i++){
     ss[i]=salles[i];}
            } catch (JSONException e) {
                e.printStackTrace();
            }



            return ss;
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

    private static class AsyncTaskSelectr extends AsyncTask<String, String, String> {

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
