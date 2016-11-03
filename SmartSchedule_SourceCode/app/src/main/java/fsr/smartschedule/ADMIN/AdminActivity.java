package fsr.smartschedule.ADMIN;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fsr.smartschedule.CLASS.AS;
import fsr.smartschedule.CLASS.Reservation;
import fsr.smartschedule.CLASS.User;
import fsr.smartschedule.LoginInterface;
import fsr.smartschedule.R;
import fsr.smartschedule.UTILE.Utile;
import fsr.smartschedule.UTILE.fragAdmin;
import fsr.smartschedule.UTILE.persoRes;
import fsr.smartschedule.UTILE.persoUser;
import fsr.smartschedule.frag3act;

public class AdminActivity extends ActionBarActivity {
    static AsyncTaskUser u;
    static ListView v;
    String location;
    static ListView salles;
    static String[] salleAdapt;
    DrawerLayout d;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;
    static User[] users;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    static AsyncTaskValid valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
  u=new AsyncTaskUser();
         d=(DrawerLayout)findViewById(R.id.drawer_layoutt);
        fragAdmin ff=new fragAdmin();

        getFragmentManager().beginTransaction().replace(R.id.ggg,ff).addToBackStack(null).commit();
  valid=new AsyncTaskValid();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        location="Annexe 1";
        salleAdapt=new String[]{};
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        u=new AsyncTaskUser();
        users= new User[]{new User("e", "e", "e", "e","e")};
        try {
            users =u.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    public void showCalendarAdmin(View v){

        d.openDrawer(Gravity.RIGHT);
    }

    public void showMenuAdmin(View v){

        d.openDrawer(Gravity.LEFT);
    }
    public void deconnexionAdmin(View v) {
        Utile.email="false";
        Utile.username="false";
        finish();
        startActivity(new Intent(this, LoginInterface.class));


    }
    public void frag3LocAnnexe1(View v){
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

    public void frag3LocAnnexe2(View v){
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

    public void frag3LocCentrale(View v){
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
    //public static void back(){Obac}
    protected void onPause()
    {
        super.onPause();
        //closing transition animations
        overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
    }

    public void ajouterSalle(View v){
        final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        LinearLayout lay = new LinearLayout(getApplicationContext());
        builder1.setView(lay);
        lay.setOrientation(LinearLayout.VERTICAL);
        final Spinner locationSpinner=new Spinner(this);
        locationSpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, new String[]{"Annexe 1","Annexe 2","Centrale"}));
        lay.addView(locationSpinner);
        //locationSpinner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,10));
        final EditText nomSalle=new EditText(this);
        final Spinner TypeSpinner=new Spinner(this);
        TypeSpinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, new String[]{"Salle de td","Salle de tp","Amphie"}));
        lay.addView(TypeSpinner);
        TextView etiquette=new TextView(this);
        builder1.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
              String nom=nomSalle.getText().toString();
                String location=locationSpinner.getSelectedItem().toString();
                 String type=TypeSpinner.getSelectedItem().toString();
                AsyncTaskSalleIns insertSalle=new AsyncTaskSalleIns();
                insertSalle.execute("nom="+nom+"&location="+location+"&type="+type);
            }
        });
        lay.addView(nomSalle);
        builder1.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin, menu);

        return true;
    }
    public void ref(View v){
        finish();
        startActivity(new Intent(this,AdminActivity.class));
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
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return PlaceholderFragment.newInstance(position + 1);
                case 1:
                    return PlaceholderFragment1.newInstance(position + 1);
                case 2:
                    return PlaceholderFragment2.newInstance(position + 1);
            }
            return PlaceholderFragment1.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
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
            View rootView = inflater.inflate(R.layout.fragment_admin, container, false);
            v=(ListView)rootView.findViewById(R.id.Useres);
       AsyncTaskUser z=new AsyncTaskUser();
users=new User[]{};
            try {
                users=z.execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if(users.length==0){users=new User[]{new User("no connexion","","","","")};}
            v.setAdapter(new persoUser(rootView.getContext(), android.R.layout.simple_list_item_1,users));


            v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
                    final AlertDialog.Builder builder1 = new AlertDialog.Builder(parent.getContext());
                    final User s= (User)parent.getItemAtPosition(position);



                    builder1.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            new AsyncTaskValid();
                            AsyncTaskValid valid=new AsyncTaskValid();
                            valid.execute("email="+s.get_email(),"http://192.168.43.105/pfe/users_valid.php");
                            builder1.setCancelable(true);
                            AsyncTaskUser aa=new AsyncTaskUser();
                            User[] uu={new User("no connection","","","","")};
                            try {
                                 uu=aa.execute().get();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                            v.setAdapter(new persoUser(parent.getContext(), android.R.layout.simple_list_item_1,uu));

                        }
                    });
                    builder1.setNegativeButton("Supprimer", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            AsyncTaskSupp sup = new AsyncTaskSupp();
                            sup.execute("email=" + s.get_email(),"http://192.168.43.105/pfe/users_supp.php");
                            AsyncTaskUser aa=new AsyncTaskUser();
                            User[] uu={new User("no connection","","","","")};
                            try {
                                uu=aa.execute().get();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                            v.setAdapter(new persoUser(parent.getContext(), android.R.layout.simple_list_item_1,uu));
                        }
                    });
                    builder1.setNeutralButton("Annuler", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

                    builder1.show();
                }
            });

            return rootView;
        }

    }

    public static class PlaceholderFragment1 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment1 newInstance(int sectionNumber) {
            PlaceholderFragment1 fragment = new PlaceholderFragment1();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment1() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragmentadmin1, container, false);
            final ListView l=(ListView)rootView.findViewById(R.id.reservation);
            Res r=new Res();
            Reservation[] ra= new Reservation[]{};
            Reservation[] raa=new Reservation[]{};

            try {
                raa=r.execute().get();if(raa!=null) ra=raa;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            l.setAdapter(new persoRes(rootView.getContext(),0,ra));
            l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
                    final AlertDialog.Builder builder1 = new AlertDialog.Builder(parent.getContext());
                    final Reservation s = (Reservation) parent.getItemAtPosition(position);


                    builder1.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            new AsyncTaskValid();
                            AsyncTaskValid valid = new AsyncTaskValid();
                             valid.execute("email="+s.getId(),"http://192.168.43.105/pfe/res_valid.php");
                            builder1.setCancelable(true);
                            Res aa = new Res();
                            Reservation[] uu = new Reservation[]{new Reservation("no connection", "", "", "", "", new AS("", ""), "", "")};
                            try {
                                uu = aa.execute().get();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                            l.setAdapter(new persoRes(parent.getContext(), android.R.layout.simple_list_item_1, uu));
                        }
                    });
                    builder1.setNegativeButton("Supprimer", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            AsyncTaskSupp sup = new AsyncTaskSupp();
                            sup.execute("email=" + s.getId(),"http://192.168.43.105/pfe/res_supp.php");
                            Res aa = new Res();
                            Reservation[] uu = new Reservation[]{new Reservation("no connection", "", "", "", "", new AS("", ""), "", "")};
                            try {
                                uu = aa.execute().get();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                            l.setAdapter(new persoRes(parent.getContext(), android.R.layout.simple_list_item_1, uu));
                        }
                    });
                    builder1.setNeutralButton("Annuler", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

                    builder1.show();
                }
            });
            return rootView;
        }
    }

    public static class PlaceholderFragment2 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment2 newInstance(int sectionNumber) {
            PlaceholderFragment2 fragment = new PlaceholderFragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment2() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragmentadmin2, container, false);
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
           // final EditText rechSalle = (EditText)rootView.findViewById(R.id.rechSal);
/*            rechSalle.addTextChangedListener(new TextWatcher(){


                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                public void onTextChanged(CharSequence s, int start, int before, int count){
                    String rech=rechSalle.getText().toString();
                    String[] SalleAdapt2=new String[salleAdapt.length];int j=0;
                    for(int i=0;i<salleAdapt.length;i++){
                        if(salleAdapt[i].toLowerCase().contains(rech.toLowerCase())){
                            SalleAdapt2[j]=salleAdapt[i];j++;
                        }
                        salles.setAdapter(new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, SalleAdapt2));
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });*/
            return rootView;
        }
    }

    private static class AsyncTaskUser extends AsyncTask<String, String, User[]> {



        @Override
        protected User[] doInBackground(String... params) {

            // URL url = null;
            String result = "",r="";User[] users=null;
            try {



                URL url = new URL("http://192.168.43.105/pfe/select_all_users.php");
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
                users= new User[json.length()];int j=0;
                for(int i=0;i<json.length();i++) {

                    JSONObject jsonn = (JSONObject) json.get(i);
                    String nom = (String) jsonn.get("nom");
                    String email = (String) jsonn.get("email");
                    String mdp = (String) jsonn.get("mdp");
                    String dept = (String) jsonn.get("dept");
                    String v=(String)jsonn.get("valid");
                        users[i]=new User(email,v,dept,mdp,nom);



                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return users;
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

    private static class AsyncTaskSupp extends AsyncTask<String, String, String> {

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

    private static class AsyncTaskSalleIns extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(String... params) {

            URL url = null;String result = "";
            try {

                url = new URL("http://192.168.43.105/pfe/insert_SA.php");


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

