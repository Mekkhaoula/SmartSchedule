package fsr.smartschedule.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;
import java.util.LinkedList;

import fsr.smartschedule.CLASS.AS;
import fsr.smartschedule.CLASS.Reservation;

/**
 * Created by ENVY on 10/05/2015.
 */
public class ReservationBD extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME="t.db";
    public static final String TABLE_RESERVATION="RESRVATATION";
    public  static final String COLUMN_ID="id";
    public static final String COLUMN_USER="user";
    public static final String COLUMN_DAY="day";
    public static final String COLUMN_TIME="time";
    public static final String COLUMN_AS="_as";
    public static  final String COLUMN_V="v";
    public static  final String COLUMN_LOCATION="location";
    public static  final String COLUMN_TIMER="timer";

    public ReservationBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="CREATE TABLE " + TABLE_RESERVATION + " (" + COLUMN_ID +" TEXT PRIMARY KEY ," +COLUMN_USER + " TEXT ," +COLUMN_DAY+ " TEXT ,"
                +COLUMN_TIME+ " TEXT ,"+COLUMN_AS+" TEXT ,"+COLUMN_LOCATION+" TEXT ,"+COLUMN_V+" TEXT ,"+COLUMN_TIMER+" TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATION);
        onCreate(db);
    }

    //Add a new row to the database

    public void addReservation(Reservation r){


        ContentValues values=new ContentValues();
        values.put(COLUMN_ID,r.getId());
        values.put(COLUMN_USER,r.getUser());
        values.put(COLUMN_DAY,r.getDay());
      //  values.put(COLUMN_TIME,r.getTime());
        values.put(COLUMN_AS,r.getSa().get_name());
        values.put(COLUMN_V,"nv");
        values.put(COLUMN_LOCATION,r.getSa().get_location());
        Calendar a=Calendar.getInstance();

        String d=String.format(1+(a.getTimeInMillis()/(60000*60))%24+":"+(a.getTimeInMillis()/60000)%60+"");
        values.put(COLUMN_TIMER,d);

        SQLiteDatabase db=getWritableDatabase();

        db.insert(TABLE_RESERVATION,null,values);

        db.close();
    }

    //Delete a product from database
  /*  public void deleteReservation(String id){
        SQLiteDatabase db=getWritableDatabase();
        String query = String.format(
                "Delete FROM \"%s\" WHERE \"%s\" = \"%s\"",
                TABLE_RESERVATION.replaceAll("\"", "\"\""),
                COLUMN_ID.replaceAll("\"", "\"\""),
                id.replaceAll("\"", "\"\""));
        db.execSQL(query);

    }*/
   /* public LinkedList<Reservation> getRESERVATION(String as){
        LinkedList<Reservation> a =new LinkedList<>();
        String name="null";
        String loc="null";
        String id="null";
        SQLiteDatabase db=getWritableDatabase();
        String query = String.format(
                "Select * FROM \"%s\" WHERE \"%s\" = \"%s\"",
                TABLE_RESERVATION.replaceAll("\"", "\"\""),
                COLUMN_LOCATION.replaceAll("\"", "\"\""),
                as.replaceAll("\"", "\"\""));

        Cursor c=db.rawQuery(query, null);

        c.moveToFirst();
        while(!c.isLast()){
            if(c.getString(c.getColumnIndex(COLUMN_ID))!=null) {
                String idd=c.getString(c.getColumnIndex(COLUMN_ID));
                String userr=c.getString(c.getColumnIndex(COLUMN_USER));
                String timee=c.getString(c.getColumnIndex(COLUMN_TIME));
                String dayy=c.getString(c.getColumnIndex(COLUMN_DAY));
                String ass=c.getString(c.getColumnIndex(COLUMN_AS));

                String daynumm=c.getString(c.getColumnIndex(COLUMN_DAYNUM));

                String lacationn=c.getString(c.getColumnIndex(COLUMN_LOCATION));
                a.add(new Reservation(userr,dayy,timee,new AS(ass,lacationn),Integer.valueOf(daynumm)));}
            c.moveToNext();

        }
        String idd=c.getString(c.getColumnIndex(COLUMN_ID));
        String userr=c.getString(c.getColumnIndex(COLUMN_USER));
        String timee=c.getString(c.getColumnIndex(COLUMN_TIME));
        String dayy=c.getString(c.getColumnIndex(COLUMN_DAY));
        String ass=c.getString(c.getColumnIndex(COLUMN_AS));

        String daynumm=c.getString(c.getColumnIndex(COLUMN_DAYNUM));

        String lacationn=c.getString(c.getColumnIndex(COLUMN_LOCATION));
        a.add(new Reservation(userr,dayy,timee,new AS(ass,lacationn),Integer.valueOf(daynumm)));
        return a;}*/
    //get reservation by AS
  /*  public LinkedList<Reservation> getReservationByAS(String as){
        LinkedList<Reservation> a =new LinkedList<>();
        String name="null";
        String loc="null";
        String id="null";
        SQLiteDatabase db=getWritableDatabase();
        String query = String.format(
                "Select * FROM \"%s\" WHERE \"%s\" = \"%s\"",
                TABLE_RESERVATION.replaceAll("\"", "\"\""),
                COLUMN_AS.replaceAll("\"", "\"\""),
                as.replaceAll("\"", "\"\""));

        Cursor c=db.rawQuery(query, null);

        c.moveToFirst();
        while(!c.isLast()){
            if(c.getCount()!=0) {
                String idd=c.getString(c.getColumnIndex(COLUMN_ID));
                String userr=c.getString(c.getColumnIndex(COLUMN_USER));
                String timee=c.getString(c.getColumnIndex(COLUMN_TIME));
                String dayy=c.getString(c.getColumnIndex(COLUMN_DAY));
                String ass=c.getString(c.getColumnIndex(COLUMN_AS));

                String daynumm=c.getString(c.getColumnIndex(COLUMN_DAYNUM));

                String lacationn=c.getString(c.getColumnIndex(COLUMN_LOCATION));
                a.add(new Reservation(userr,dayy,timee,new AS(ass,lacationn),Integer.valueOf(daynumm)));}
            c.moveToNext();

        }
        String idd=c.getString(c.getColumnIndex(COLUMN_ID));
        String userr=c.getString(c.getColumnIndex(COLUMN_USER));
        String timee=c.getString(c.getColumnIndex(COLUMN_TIME));
        String dayy=c.getString(c.getColumnIndex(COLUMN_DAY));
        String ass=c.getString(c.getColumnIndex(COLUMN_AS));

        String daynumm=c.getString(c.getColumnIndex(COLUMN_DAYNUM));

        String lacationn=c.getString(c.getColumnIndex(COLUMN_LOCATION));
        a.add(new Reservation(userr,dayy,timee,new AS(ass,lacationn),Integer.valueOf(daynumm)));
        return a;}

    public LesReservation getAllRESERVATION(){
        LesReservation a =new LesReservation();
        String name="null";
        String loc="null";
        String id="null";
        SQLiteDatabase db=getWritableDatabase();
        String query = String.format(
                "Select * FROM \"%s\" WHERE \"%s\" = \"%s\"",
                TABLE_RESERVATION.replaceAll("\"", "\"\""),
                COLUMN_LOCATION.replaceAll("\"", "\"\""),
                COLUMN_LOCATION.replaceAll("\"", "\"\""));

        Cursor c=db.rawQuery(query, null);

        c.moveToFirst();
        while(!c.isLast() && c.getCount()!=0){
            if(c.getString(c.getColumnIndex(COLUMN_ID))!=null) {
                String idd=c.getString(c.getColumnIndex(COLUMN_ID));
                String userr=c.getString(c.getColumnIndex(COLUMN_USER));
                String timee=c.getString(c.getColumnIndex(COLUMN_TIME));
                String dayy=c.getString(c.getColumnIndex(COLUMN_DAY));
                String ass=c.getString(c.getColumnIndex(COLUMN_AS));

                String daynumm=c.getString(c.getColumnIndex(COLUMN_DAYNUM));

                String lacationn=c.getString(c.getColumnIndex(COLUMN_LOCATION));
                a.add(new Reservation(userr,dayy,timee,new AS(ass,lacationn),Integer.valueOf(daynumm)));}
            c.moveToNext();

        }
        if(c.getCount()!=0){
            String idd=c.getString(c.getColumnIndex(COLUMN_ID));
            String userr=c.getString(c.getColumnIndex(COLUMN_USER));
            String timee=c.getString(c.getColumnIndex(COLUMN_TIME));
            String dayy=c.getString(c.getColumnIndex(COLUMN_DAY));
            String ass=c.getString(c.getColumnIndex(COLUMN_AS));

            String daynumm=c.getString(c.getColumnIndex(COLUMN_DAYNUM));

            String lacationn=c.getString(c.getColumnIndex(COLUMN_LOCATION));
            a.add(new Reservation(userr,dayy,timee,new AS(ass,lacationn),Integer.valueOf(daynumm)));}
        return a;}

    public void deleteAll(){
        String query = String.format(
                "Delete FROM \"%s\" WHERE 1",
                TABLE_RESERVATION.replaceAll("\"", "\"\""));
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);

    }

    //Data base to string
*/
    public String databaseToString(){
        String dbString="";
        SQLiteDatabase db=getWritableDatabase();
        //1=dynamique var ????!
        String z="b";
        String query = "Select * from "+TABLE_RESERVATION+" WHERE 1";
        //Cursor point to the first result

        Cursor c=db.rawQuery(query, null);
        //Move to the first row in result
        c.moveToFirst();


        while(!c.isLast()){
            if(c.getString(c.getColumnIndex(COLUMN_ID))!=null && !c.isLast()) {
                dbString+=c.getString(c.getColumnIndex(COLUMN_ID));
                dbString+="\t";
                dbString+=c.getString(c.getColumnIndex(COLUMN_USER));
                dbString+="\t";
                dbString+=c.getString(c.getColumnIndex(COLUMN_TIME));
                dbString+="\t";
                dbString+=c.getString(c.getColumnIndex(COLUMN_DAY));

                dbString+="\t";
                dbString+=c.getString(c.getColumnIndex(COLUMN_AS));
                dbString+="\t";
                dbString+=c.getString(c.getColumnIndex(COLUMN_V));

                dbString+="\t";
                dbString+=c.getString(c.getColumnIndex(COLUMN_TIMER));
                dbString+="\t";
                dbString+=c.getString(c.getColumnIndex(COLUMN_LOCATION));
                dbString+="\n";

                c.moveToNext();
            }
        }
        db.close();
        dbString+=c.getString(c.getColumnIndex(COLUMN_ID));
        dbString+="\t";
        dbString+=c.getString(c.getColumnIndex(COLUMN_USER));
        dbString+="\t";
        dbString+=c.getString(c.getColumnIndex(COLUMN_TIME));
        dbString+="\t";
        dbString+=c.getString(c.getColumnIndex(COLUMN_DAY));

        dbString+="\t";
        dbString+=c.getString(c.getColumnIndex(COLUMN_AS));
        dbString+="\t";
        dbString+=c.getString(c.getColumnIndex(COLUMN_V));
        dbString+="\t";
        dbString+=c.getString(c.getColumnIndex(COLUMN_TIMER));
        dbString+="\t";
        dbString+=c.getString(c.getColumnIndex(COLUMN_LOCATION));
        dbString+="\n";


        return  dbString;
    }
/*
    public boolean existe(Reservation r){
        Reservation u=new Reservation("null","null","null",new AS(),400);
        SQLiteDatabase db=getWritableDatabase();
        String query = String.format(
                "Select * FROM \"%s\" WHERE \"%s\" = \"%s\" AND \"%s\" = \"%s\"  AND \"%s\" = \"%s\" AND \"%s\" = \"%s\"",
                TABLE_RESERVATION.replaceAll("\"", "\"\""),

                COLUMN_DAY.replaceAll("\"", "\"\""),
                r.getDay().replaceAll("\"", "\"\""),
                COLUMN_TIME.replaceAll("\"", "\"\""),
                r.getTime().replaceAll("\"", "\"\""),
                COLUMN_LOCATION.replaceAll("\"", "\"\""),
                r.getSa().getLocation().replaceAll("\"", "\"\""),
                COLUMN_AS.replaceAll("\"", "\"\""),
                r.getSa().getName().replaceAll("\"", "\"\""));
        Cursor c=db.rawQuery(query, null);
        c.moveToFirst();
        if(c.getCount()!=0) {
            return true;




        }
        return false;}*/

}
