package fsr.smartschedule;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutionException;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fsr.smartschedule.BD.ASBD;
import fsr.smartschedule.CLASS.AS;
import fsr.smartschedule.CLASS.Reservation;
import fsr.smartschedule.CLASS.User;
import fsr.smartschedule.Notification.NotificationActivite;
import fsr.smartschedule.RESERVATION.DoReservation;
import fsr.smartschedule.RESERVATION.FaireRes;
import fsr.smartschedule.RESERVATION.Recherche;
import fsr.smartschedule.UTILE.Favoris;
import fsr.smartschedule.UTILE.Utile;
import fsr.smartschedule.UTILE.persoRes;


public class Home2 extends ActionBarActivity {
static ResUser r;
    static Res r1;
    static Reservation[] ra;
    static Reservation[] ra1;
    static ListView salles;
    static String[] salleAdapt;
    String location;
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
        setContentView(R.layout.activity_home2);


             //frag1act.user.setText(Utile.username);
        frag3act ff=new frag3act();
        DrawerLayout d=(DrawerLayout)findViewById(R.id.drawer_layout);
       d.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        getFragmentManager().beginTransaction().replace(R.id.ggg,ff).addToBackStack(null).commit();
//final frag11act frag11=new frag11act();
        //final frag22act frag22=new frag22act();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        r=new ResUser();
        r1=new Res();
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Button button=(Button)findViewById(R.id.button33);
                if(position==0){//getFragmentManager().beginTransaction().replace(R.id.fragg, new frag1act()).addToBackStack(null).commit();
                    button.setText("Mon Schedule");}
               // if(position==1){//getFragmentManager().beginTransaction().replace(R.id.fragg,  frag11).addToBackStack(null).commit();
                  //  button.setText("Reserver");}
                if(position==1){//getFragmentManager().beginTransaction().replace(R.id.fragg, frag22).addToBackStack(null).commit();
                    button.setText("Chercher");}
                if(position==2){//getFragmentManager().beginTransaction().replace(R.id.frag, new frag3act()).addToBackStack(null).commit();
                    button.setText("Schedule");}
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
      ra= new Reservation[]{};
        Reservation[] raa=new Reservation[]{};

        try {
                raa=r.execute(Utile.email).get();if(raa!=null) ra=raa;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ra1= new Reservation[]{};
        Reservation[] raa1=new Reservation[]{};

        try {
            raa1=r1.execute().get();if(raa!=null) ra1=raa1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }




//        salle.setAdapter(new persoRes(this, android.R.layout.simple_list_item_1, new Reservation[]{new Reservation("a","a","a","a","a",new AS("a","a"),"a","a")}));
        TextView vv=(TextView)findViewById(R.id.textView15);


    }

    public void refresh(View v){//finish();
    startActivity(new Intent(this,FaireRes.class));
   };
    public void frag2LocAnnexe1(View v){
        location="Annexe 1";
        AsyncTaskSalle ats=new AsyncTaskSalle();String[] s=new String[]{};
        try {
            salleAdapt=ats.execute("Annexe 1").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        salles.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, salleAdapt));

    }

    public void frag2LocAnnexe2(View v){
        location="Annexe 2";
        AsyncTaskSalle ats=new AsyncTaskSalle();String[] s=new String[]{};
        try {
            salleAdapt=ats.execute("Annexe 2").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        salles.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, salleAdapt));

    }

    public void frag2LocCentrale(View v){
        location="Centrale";
        AsyncTaskSalle ats=new AsyncTaskSalle();String[] s=new String[]{};
        try {
            salleAdapt=ats.execute("Centrale").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        salles.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, salleAdapt));

    }


    public void showCalendar(View v){
        DrawerLayout d=(DrawerLayout)findViewById(R.id.drawer_layout);
        d.openDrawer(Gravity.RIGHT);
        }
public void toNotif(View v){
    startActivity(new Intent(this, NotificationActivite.class));
}
    public void showMenu(View v){
        DrawerLayout d=(DrawerLayout)findViewById(R.id.drawer_layout);
        d.openDrawer(Gravity.LEFT);
    }
    public void deconnexion(View v) {
        Utile.email="false";
        Utile.username="false";
        finish();
        startActivity(new Intent(this, LoginInterface.class));


    }

    public void torech(View v){startActivity(new Intent(this, Recherche.class));}
    public void tof(View v){
        startActivity(new Intent(this, fffffffffffffffffffffffffffffffffffffff.class));}

    public void toFav(View v){startActivity(new Intent(this, Favoris.class));}

    public void toResa(View v){
        mViewPager.setCurrentItem(1);
    }

    public void toleft(View v){

        mViewPager.setCurrentItem((mViewPager.getCurrentItem()+1)%4);
    }
    public void toright(View v){

        mViewPager.setCurrentItem((mViewPager.getCurrentItem()-1)%4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home2, menu);
        return true;
    }

    public void dec(View v){startActivity(new Intent(this,LoginInterface.class));
        finish();}

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
           if(position==0){return CalendarFragment.newInstance(position + 1);}
           // else if (position==1){return MakevFragment.newInstance(position + 1);}
            else if (position==1){return RechFragment.newInstance(position + 1);}
           else  {return ListevFragment.newInstance(position + 1);}


        }

        @Override
        public int getCount() {
            return 3;
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


    public void toDores(View v){startActivity(new Intent(this,FaireRes.class));}

    public static class RechFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static RechFragment newInstance(int sectionNumber) {
            RechFragment fragment = new RechFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public RechFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment22, container, false);
            salles=(ListView)rootView.findViewById(R.id.listView5);
            AsyncTaskSalle ats=new AsyncTaskSalle();String[] s=new String[]{};
            try {
                salleAdapt=ats.execute("Annexe 1").get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            salles.setAdapter(new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, salleAdapt));


            salles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    startActivity(new Intent(rootView.getContext(),Recherche.class));
                }
            });
            return rootView;
        }
    }


    public static class MakevFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static MakevFragment newInstance(int sectionNumber) {
            MakevFragment fragment = new MakevFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public MakevFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment11, container, false);
            return rootView;
        }
    }


    public static class ListevFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ListevFragment newInstance(int sectionNumber) {
            ListevFragment fragment = new ListevFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public ListevFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_home2, container, false);
            final ListView l=(ListView)rootView.findViewById(R.id.listView4);

            r1=new Res();
            try {ResUser r=new ResUser();
                Reservation[] raa=r1.execute().get();if(raa!=null) ra1=raa;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();

            }
            l.setAdapter(new persoRes(rootView.getContext(),0,ra1));
            final SwipeRefreshLayout swipe=(SwipeRefreshLayout)rootView.findViewById(R.id.swipe_container);
            swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    r1=new Res();
                    try {ResUser r=new ResUser();
                        Reservation[] raa=r1.execute().get();if(raa!=null) ra1=raa;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();

                    }
                    l.setAdapter(new persoRes(rootView.getContext(),0,ra1));
                    swipe.setRefreshing(false);
                }
            });
            return rootView;
        }
    }

    public static class CalendarFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static CalendarFragment newInstance(int sectionNumber) {
            CalendarFragment fragment = new CalendarFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public CalendarFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.mesreservatiobfrag, container, false);



          // Reservation[] ra=new Reservation[]{new Reservation("id","use","day","he","he",new AS("z","b"),"j","f"),new Reservation("id","use","day","he","he",new AS("z","b"),"j","f"),new Reservation("id","use","day","he","he",new AS("z","b"),"j","f"),new Reservation("id","use","day","he","he",new AS("z","b"),"j","f"),new Reservation("id","use","day","he","he",new AS("z","b"),"j","f"),new Reservation("id","use","day","he","he",new AS("z","b"),"j","f")};
            final ListView l=(ListView)rootView.findViewById(R.id.listView);
            try {ResUser r=new ResUser();
                Reservation[] raa=r.execute("user="+Utile.email).get();if(raa!=null) ra=raa;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();

            }
            l.setAdapter(new persoRes(rootView.getContext(),0,ra));
            final SwipeRefreshLayout swipe=(SwipeRefreshLayout)rootView.findViewById(R.id.swipe_container1);
            swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    try {ResUser r=new ResUser();
                       Reservation[] raa=r.execute("user="+Utile.email).get();if(raa!=null) ra=raa;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();

                    }
                    l.setAdapter(new persoRes(rootView.getContext(),0,ra));
                    swipe.setRefreshing(false);
                }
            });
            return rootView;
        }
    }

    private static class Res extends AsyncTask<String, String, Reservation[]> {



        @Override
        protected Reservation[] doInBackground(String... params) {

            // URL url = null;
            String result = "",r="";Reservation[] salles=null;
            try {



                URL url = new URL("http://192.168.43.105/pfe/select_res.php");
                URLConnection conn = url.openConnection();


                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result+=line;
                    r=result;

                }
                rd.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Reservation[] salles2={};
            try {
                JSONArray json = new JSONArray(result);
                salles=new Reservation[json.length()];int j=0;
                for(int i=0;i<json.length();i++) {

                    JSONObject jsonn = (JSONObject) json.get(i);
                    String id = (String) jsonn.get("id");
                    //String location = (String) jsonn.get("location");
                    String user = (String) jsonn.get("user");
                    String day = (String) jsonn.get("day");
                    String heuredebut = (String) jsonn.get("heure");
                    String desc= (String) jsonn.get("desc");
                    AS as = new AS((String) jsonn.get("as"),(String) jsonn.get("location"));
                    String v = (String) jsonn.get("v");
                    String timer = (String) jsonn.get("timer");

                    salles[i]=new Reservation(id,user,day,heuredebut,desc,as,v,timer);


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

    private static class ResUser extends AsyncTask<String, String, Reservation[]> {



        @Override
        protected Reservation[] doInBackground(String... params) {

            // URL url = null;
            String result = "",r="";Reservation[] salles=null;
            // URL url = null;

            try {



                URL url = new URL("http://192.168.43.105/pfe/select_res_user.php");

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
            Reservation[] salles2={};
            try {
                JSONArray json = new JSONArray(result);
                salles=new Reservation[json.length()];int j=0;
                for(int i=0;i<json.length();i++) {

                    JSONObject jsonn = (JSONObject) json.get(i);
                    String id = (String) jsonn.get("id");
                    //String location = (String) jsonn.get("location");
                    String user = (String) jsonn.get("user");
                    String day = (String) jsonn.get("day");
                    String heuredebut = (String) jsonn.get("heure");
                    String desc= (String) jsonn.get("desc");
                    AS as = new AS((String) jsonn.get("as"),(String) jsonn.get("location"));
                    String v = (String) jsonn.get("v");
                    String timer = (String) jsonn.get("timer");

                    salles[i]=new Reservation(id,user,day,heuredebut,desc,as,v,timer);


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
}




