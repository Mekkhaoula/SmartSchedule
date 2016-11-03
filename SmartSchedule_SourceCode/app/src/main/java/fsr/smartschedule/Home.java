package fsr.smartschedule;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Intent;
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
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fsr.smartschedule.ADMIN.AdminActivity;
import fsr.smartschedule.CLASS.AS;
import fsr.smartschedule.CLASS.Reservation;
import fsr.smartschedule.CLASS.User;
import fsr.smartschedule.UTILE.Utile;
import fsr.smartschedule.UTILE.persoRes;


public class Home extends ActionBarActivity {
String location;
    static ListView salles;
    static String[] salleAdapt;
    static Res r1;
    static Reservation[] ra1;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;
    static int i=0;
    /**
     * The {@link ViewPager} that will host the section contents.
     */

    ViewPager mViewPager;
   // frag1act frag1act=new frag1act();
    //frag2act frag2act=new frag2act();
   // frag3act frag3act=new frag3act();

    @Override
    public void onBackPressed() {
        i=0;
        super.onBackPressed();
    }


    public  void toHome2(View v) throws ExecutionException, InterruptedException {
        EditText userName = (EditText) findViewById(R.id.textView6);
        EditText mdp = (EditText) findViewById(R.id.textView8);
        AsyncTaskSelectUser check=new AsyncTaskSelectUser();
        User[] result=check.execute("email="+userName.getText().toString()+"&mdp="+mdp.getText().toString()).get();
        if(userName.getText().toString().equals("admin") && mdp.getText().toString().equals("admin")){
            Utile.email="admin";
            Utile.username="admin";
            startActivity(new Intent(this,AdminActivity.class));
            finish();
        }
        else  if(result.length==0) {
            Toast.makeText(getApplicationContext(), "Invalide email ou mot de passe.",
                    Toast.LENGTH_SHORT).show();
        }
        else if(result[0].get_email().equals("false")){
            Toast.makeText(getApplicationContext(), "Echec de connexion",
                    Toast.LENGTH_SHORT).show();
        }
        else if(result[0].get_valid().equals("nv")){
            Toast.makeText(getApplicationContext(), "Votre compte n'est pas encore valider",
                    Toast.LENGTH_SHORT).show();
        }

        else if(result[0].get_valid().equals("v")){
            Utile.email=result[0].get_email();
            Utile.username=result[0].get_name();
            startActivity(new Intent(this,Home2.class));
            finish();
        }
        else if(userName.getText().toString().equals("admin") && mdp.getText().toString().equals("admin")){
            Utile.email="admin";
            Utile.username="admin";
            startActivity(new Intent(this,AdminActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // Fragment frag1act=new  loginFrag();
       // getFragmentManager().beginTransaction().replace(R.id.gg,new frag3act()).addToBackStack(null).commit();

        location="Annexe 1";
        salleAdapt=new String[]{};
        DrawerLayout d=(DrawerLayout)findViewById(R.id.drawer_layout);
       // d.showContextMenu();
//        d.openDrawer(Gravity.LEFT);
       // d.closeDrawer(Gravity.LEFT);


      //  getFragmentManager().beginTransaction().replace(R.id.frag, new frag2act()).addToBackStack(null).commit();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
       mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(int position) {
               Button button=(Button)findViewById(R.id.button3);

               if(position==0){//getFragmentManager().beginTransaction().replace(R.id.frag,frag1act).addToBackStack(null).commit();
               button.setText("Connexion");}
               if(position==1){//getFragmentManager().beginTransaction().replace(R.id.frag,  frag2act).addToBackStack(null).commit();
                   button.setText("Reservations");}
               if(position==2){//getFragmentManager().beginTransaction().replace(R.id.frag, frag3act).addToBackStack(null).commit();
                   button.setText("Recherche");}
           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });

  mViewPager.setCurrentItem(0);

    }

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
    public void toLeft(View v){

        mViewPager.setCurrentItem((mViewPager.getCurrentItem()+1)%3);
    }
    public void tf(View c){startActivity(new Intent(this,fffffffffffffffffffffffffffffffffffffff.class));}
    public void toRight(View v){

        mViewPager.setCurrentItem((mViewPager.getCurrentItem()-1)%3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void toLogin(View v){startActivity(new Intent(this,LoginInterface.class));}

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

    public void to(View v){startActivity(new Intent(getApplicationContext(),Home2.class));}


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
            if(position==0)     {


                return Fragment1.newInstance(position + 1);}
            else if(position==1){
               // getFragmentManager().beginTransaction().replace(R.id.frag, new TextFragmen()).addToBackStack(null).commit();
                return Fragment2.newInstance(position + 1);
               }else {

                return Fragment3.newInstance(position + 1);}
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

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
    public static class Fragment1 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static Fragment1 newInstance(int sectionNumber) {
            Fragment1 fragment = new Fragment1();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public Fragment1() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment1act, container, false);
            return rootView;
        }
    }

    public static class Fragment2 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static Fragment2 newInstance(int sectionNumber) {
            Fragment2 fragment = new Fragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public Fragment2() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment2, container, false);
            final ListView l=(ListView)rootView.findViewById(R.id.listView4);
ra1=null;
            r1=new Res();
            try {
                Reservation[] raa=r1.execute().get();if(raa!=null) ra1=raa;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();

            }
            if(ra1!=null)
            l.setAdapter(new persoRes(rootView.getContext(),0,ra1));
            final SwipeRefreshLayout swipe=(SwipeRefreshLayout)rootView.findViewById(R.id.swipe_container1);
            swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    r1=new Res();
                    ra1=new Reservation[1];
                    try {
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

    public static class Fragment3 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static Fragment3 newInstance(int sectionNumber) {
            Fragment3 fragment = new Fragment3();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public Fragment3() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment3, container, false);
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
           final EditText rechSalle = (EditText)rootView.findViewById(R.id.rechSal);
            rechSalle.addTextChangedListener(new TextWatcher(){


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
            });
            return rootView;
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

    private class AsyncTaskSelectUser extends AsyncTask<String, String, User[]> {


        @Override
        protected User[] doInBackground(String... params) {

            // URL url = null;
            String result = "",r="";User[] users=null;
            try {



                URL url = new URL("http://192.168.43.105/pfe/select_user_ex.php");

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
            users= new User[]{new User("false", "false", "false", "false", "false")};
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

            if(users==null) return new User[]{new User("false", "false", "false", "false", "false")};

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


    /**
     *
     * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
     */

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
}}



