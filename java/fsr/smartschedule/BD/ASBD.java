package fsr.smartschedule.BD;

/**
 * Created by ENVY on 10/05/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fsr.smartschedule.CLASS.AS;
import fsr.smartschedule.UTILE.LesSalles;

public class ASBD extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME="ASBdd.db";
    public static final String TABLE_ASBD="ASBD";
    public  static final String COLUMN_ID="_id";
    public static final String COLUMN_NAME="_name";
    public static final String COLUMN_LOCATION="_location";

    public ASBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="CREATE TABLE " + TABLE_ASBD + " (" + COLUMN_ID +" TEXT PRIMARY KEY ," +COLUMN_NAME + " TEXT ," +COLUMN_LOCATION+ " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASBD);
        onCreate(db);

    }

    //Add a new row to the database

    public void addAS(AS as){


        ContentValues values=new ContentValues();
        values.put(COLUMN_ID,as.get_id());
        values.put(COLUMN_NAME,as.get_name());
        values.put(COLUMN_LOCATION,as.get_location());

        SQLiteDatabase db=getWritableDatabase();

        db.insert(TABLE_ASBD,null,values);


    }
/*
    //Delete a product from database
    public void deleteAS(String id){
        SQLiteDatabase db=getWritableDatabase();
        String query = String.format(
                "Delete FROM \"%s\" WHERE \"%s\" = \"%s\"",
                TABLE_ASBD.replaceAll("\"", "\"\""),
                COLUMN_ID.replaceAll("\"", "\"\""),
                id.replaceAll("\"", "\"\""));
        db.execSQL(query);

    }*/
    public LesSalles getASparLoc(String location){
        LesSalles a=new LesSalles();
        String name="null";
        String loc="null";

        SQLiteDatabase db=getWritableDatabase();
        String query = String.format(
                "Select * FROM \"%s\" WHERE \"%s\" = \"%s\"",
                TABLE_ASBD.replaceAll("\"", "\"\""),
                COLUMN_LOCATION.replaceAll("\"", "\"\""),
                location.replaceAll("\"", "\"\""));

        Cursor c=db.rawQuery(query, null);
        int i=0;
        c.moveToFirst();
        while(c.getCount()!=0 && !c.isLast()){



            name=c.getString(c.getColumnIndex(COLUMN_NAME));

            loc=c.getString(c.getColumnIndex(COLUMN_LOCATION));
            a.add(new AS(name,loc));
            i++;
            c.moveToNext();

        }

        if(c.getCount()!=0) {

            name=c.getString(c.getColumnIndex(COLUMN_NAME));

            loc=c.getString(c.getColumnIndex(COLUMN_LOCATION));
            a.add(new AS(name,loc));


        }
        //get AS by nom

        return a;}
    /*
    public LesSalles getASparNom(String nom){
        LesSalles a=new LesSalles();
        String name="null";
        String loc="null";

        SQLiteDatabase db=getWritableDatabase();
        String query = String.format(
                "Select * FROM \"%s\" WHERE \"%s\" = \"%s\"",
                TABLE_ASBD.replaceAll("\"", "\"\""),
                COLUMN_NAME.replaceAll("\"", "\"\""),
                nom.replaceAll("\"", "\"\""));

        Cursor c=db.rawQuery(query, null);
        int i=0;
        c.moveToFirst();
        while(!c.isLast() && c.getCount()!=0
                ){
            if(c.getString(c.getColumnIndex(COLUMN_ID))!=null && !c.isLast()) {


                name=c.getString(c.getColumnIndex(COLUMN_NAME));

                loc=c.getString(c.getColumnIndex(COLUMN_LOCATION));
                a.add(new AS(name,loc));
                i++;
                c.moveToNext();
            }
        }
        db.close();
        if(  c.getCount()!=0) {

            name=c.getString(c.getColumnIndex(COLUMN_NAME));

            loc=c.getString(c.getColumnIndex(COLUMN_LOCATION));
            a.add(new AS(name,loc));


        }
        return a;}
        */
    public LesSalles getAll(){
        LesSalles a=new LesSalles();
        String name="null";
        String loc="null";

        SQLiteDatabase db=getWritableDatabase();
        String query = String.format(
                "Select * FROM \"%s\" WHERE \"%s\" = \"%s\"",
                TABLE_ASBD.replaceAll("\"", "\"\""),
                COLUMN_LOCATION.replaceAll("\"", "\"\""),
                COLUMN_LOCATION.replaceAll("\"", "\"\""));

        Cursor c=db.rawQuery(query, null);
        int i=0;
        c.moveToFirst();
        while(!c.isLast() && c.getCount()!=0){



            name=c.getString(c.getColumnIndex(COLUMN_NAME));

            loc=c.getString(c.getColumnIndex(COLUMN_LOCATION));
            a.add(new AS(name,loc));
            i++;
            c.moveToNext();

        }

        if(c.getCount()!=0) {

            name=c.getString(c.getColumnIndex(COLUMN_NAME));

            loc=c.getString(c.getColumnIndex(COLUMN_LOCATION));
            a.add(new AS(name,loc));


        }
        return a;}

    public AS getAS(String location){
        String name="null";
        String loc="null";
        String id="null";
        SQLiteDatabase db=getWritableDatabase();
        String query = String.format(
                "Select * FROM \"%s\" WHERE \"%s\" = \"%s\"",
                TABLE_ASBD.replaceAll("\"", "\"\""),
                COLUMN_ID.replaceAll("\"", "\"\""),
                location.replaceAll("\"", "\"\""));

        Cursor c=db.rawQuery(query, null);

        c.moveToFirst();
        if(c.getCount()!=0){
        if(c.getString(c.getColumnIndex(COLUMN_ID))!=null) {
            id=c.getString(c.getColumnIndex(COLUMN_ID));
            name=c.getString(c.getColumnIndex(COLUMN_NAME));

            loc=c.getString(c.getColumnIndex(COLUMN_LOCATION));


        }}
        return new AS(name,loc);}

    public void deleteAll(){
        String query = String.format(
                "Delete FROM \"%s\" WHERE 1",
                TABLE_ASBD.replaceAll("\"", "\"\""));
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);

    }

    public void delete(AS a){
        String query = String.format(
                "delete FROM \"%s\" WHERE \"%s\" = \"%s\"",
                TABLE_ASBD.replaceAll("\"", "\"\""),
                COLUMN_ID.replaceAll("\"", "\"\""),
                a.get_location()+a.get_name().replaceAll("\"", "\"\""));
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(query);

    }

    //Data base to string

    public String databaseToString(){
        String dbString="";
        SQLiteDatabase db=getWritableDatabase();
        //1=dynamique var ????!
        String z="b";
        String query = "Select * from "+TABLE_ASBD+" WHERE 1";
        //Cursor point to the first result
        Cursor c=db.rawQuery(query, null);
        //Move to the first row in result
        c.moveToFirst();


        while(!c.isLast()){
            if(c.getCount()!=0) {
                dbString+=c.getString(c.getColumnIndex(COLUMN_ID));
                dbString+="\t";
                dbString+=c.getString(c.getColumnIndex(COLUMN_NAME));
                dbString+="\t";
                dbString+=c.getString(c.getColumnIndex(COLUMN_LOCATION));
                dbString+="\n";

                c.moveToNext();
            }
        }

        dbString+=c.getString(c.getColumnIndex(COLUMN_ID));
        dbString+="\t";
        dbString+=c.getString(c.getColumnIndex(COLUMN_NAME));
        dbString+="\t";
        dbString+=c.getString(c.getColumnIndex(COLUMN_LOCATION));
        dbString+="\n";


        return  dbString;
    }

}
