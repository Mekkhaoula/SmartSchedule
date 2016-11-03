package fsr.smartschedule;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import java.util.concurrent.ExecutionException;

import fsr.smartschedule.ADMIN.AdminActivity;
import fsr.smartschedule.CLASS.User;
import fsr.smartschedule.UTILE.Utile;


public class LoginInterface extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_interface);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
        if (Utile.email != null && Utile.username != null) {
            if (Utile.email.equals("admin")){ startActivity(new Intent(this, AdminActivity.class));
            finish();}
            else if(!Utile.email.equals("false") && !Utile.username.equals("false")){
               startActivity(new Intent(this,Home2.class));finish();
            }
        }
    }


    protected void onPause()
    {
        super.onPause();
        //closing transition animations
        overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
    }
    public void signUp(View v){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

        builder1.show();
    }
    public void toHome(View v){startActivity(new Intent(getApplicationContext(),Home.class));
    finish();}

    public  void toHome2(View v) throws ExecutionException, InterruptedException {
        EditText userName = (EditText) findViewById(R.id.UserName);
        EditText mdp = (EditText) findViewById(R.id.mdp);

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
            Toast.makeText(getApplicationContext(), "Votre compte n'est pas encore validé",
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_interface, menu);
        return true;
    }
    public void toRegister(View v){startActivity(new Intent(this,register.class));}

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

    private class AsyncTaskSelectUser extends AsyncTask<String, String, User[]> {
        ProgressDialog progress;

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
            onPostExecute(result);

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

                progress.dismiss();


        }

        /**
         *
         * @see android.os.AsyncTask#onPreExecute()
         */
        @Override
        protected void onPreExecute() {
            // Things to be done before execution of long running operation. For
            // example showing ProgessDialog
            progress = new ProgressDialog(LoginInterface.this);
            progress.setMessage("Connexion en cours...");
            progress.show();

        }


        @Override
        protected void onProgressUpdate(String... text) {
            // textView.setText(text[0]);
            // Things to be done while execution of long running operation is in
            // progress. For example updating ProgessDialog
            progress.dismiss();


        }
        }


        /**
         *
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */


}
