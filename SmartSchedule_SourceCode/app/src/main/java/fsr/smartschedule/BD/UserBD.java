package fsr.smartschedule.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fsr.smartschedule.CLASS.User;

/**
 * Created by ENVY on 10/05/2015.
 */
public class UserBD extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME="a.db";
    public static final String TABLE_Users="Users";
    public  static final String COLUMN_USER="_email";
    public static final String COLUMN_MDP="_mdp";
    public static final String COLUMN_NAME="_name";
    public static final String COLUMN_DEPT="_dept";

    public UserBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="CREATE TABLE " + TABLE_Users + " (" + COLUMN_USER +" TEXT PRIMARY KEY ," +COLUMN_MDP + " TEXT ," +COLUMN_NAME+ " TEXT ,"+COLUMN_DEPT+ " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        onCreate(db);
    }

    //Add a new row to the database

    public void addUser(User user){


        ContentValues values=new ContentValues();
        values.put(COLUMN_USER,user.get_email());
        values.put(COLUMN_MDP,user.get_mdp());
        values.put(COLUMN_NAME,user.get_name());
        values.put(COLUMN_DEPT,user.get_dept());

        SQLiteDatabase db=getWritableDatabase();
        //String query="CREATE TABLE " + TABLE_Users + " (" + COLUMN_USER +" TEXT PRIMARY KEY ," +COLUMN_MDP + " TEXT);";
        // db.execSQL(query);
        db.insert(TABLE_Users,null,values);

        db.close();
    }

    //Delete a product from database
    public void deleteUser(String userName){
        SQLiteDatabase db=getWritableDatabase();
        String query = String.format(
                "Delete FROM \"%s\" WHERE \"%s\" = \"%s\"",
                TABLE_Users.replaceAll("\"", "\"\""),
                COLUMN_USER.replaceAll("\"", "\"\""),
                userName.replaceAll("\"", "\"\""));
        db.execSQL(query);

    }
 /*   public User getUser(String userName){
        String name="null";
        String mdp="null";
        SQLiteDatabase db=getWritableDatabase();
        String query = String.format(
                "Select * FROM \"%s\" WHERE \"%s\" = \"%s\"",
                TABLE_Users.replaceAll("\"", "\"\""),
                COLUMN_USER.replaceAll("\"", "\"\""),
                userName.replaceAll("\"", "\"\""));
        Cursor c=db.rawQuery(query, null);
        c.moveToFirst();
        if(c.getCount()!=0) {
            name=c.getString(c.getColumnIndex(COLUMN_USER));

            mdp=c.getString(c.getColumnIndex(COLUMN_MDP));


        }
        return new User(name,mdp);}*/

   /* public boolean existe(User user){
        User u=new User("null","null");
        SQLiteDatabase db=getWritableDatabase();
        String query = String.format(
                "Select * FROM \"%s\" WHERE \"%s\" = \"%s\" AND \"%s\" = \"%s\"",
                TABLE_Users.replaceAll("\"", "\"\""),
                COLUMN_USER.replaceAll("\"", "\"\""),
                user.get_userName().replaceAll("\"", "\"\""),
                COLUMN_MDP.replaceAll("\"", "\"\""),
                user.get_mdp().replaceAll("\"", "\"\""));
        Cursor c=db.rawQuery(query, null);
        c.moveToFirst();
        if(c.getCount()!=0) {
            u.set_userName(c.getString(c.getColumnIndex(COLUMN_USER)));

            u.set_mdp(c.getString(c.getColumnIndex(COLUMN_MDP)));


        }
        return user.equals(u);}*/

    public void deleteAll(){
        String query = String.format(
                "Delete FROM \"%s\" WHERE 1",
                TABLE_Users.replaceAll("\"", "\"\""));
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);

    }

    //Data base to string

    public String databaseToString(){
        String dbString="";
        SQLiteDatabase db=getWritableDatabase();
        //1=dynamique var ????!
        String z="b";
        String query = "Select * from "+TABLE_Users+" WHERE 1";
        //Cursor point to the first result
        Cursor c=db.rawQuery(query, null);
        //Move to the first row in result
        c.moveToFirst();


        while(!c.isLast()){
            if(c.getString(c.getColumnIndex(COLUMN_USER))!=null && !c.isLast()) {
                dbString+=c.getString(c.getColumnIndex(COLUMN_USER));
                dbString+="\t";
                dbString+=c.getString(c.getColumnIndex(COLUMN_MDP));
                dbString+="\t";
                dbString+=c.getString(c.getColumnIndex(COLUMN_NAME));
                dbString+="\t";
                dbString+=c.getString(c.getColumnIndex(COLUMN_DEPT));
                dbString+="\n";

                c.moveToNext();
            }
        }

        dbString+=c.getString(c.getColumnIndex(COLUMN_USER));
        dbString+="\t";
        dbString+=c.getString(c.getColumnIndex(COLUMN_MDP));
        dbString+="\t";
        dbString+=c.getString(c.getColumnIndex(COLUMN_NAME));
        dbString+="\t";
        dbString+=c.getString(c.getColumnIndex(COLUMN_DEPT));
        dbString+="\n";
        db.close();

        return  dbString;
    }
}
