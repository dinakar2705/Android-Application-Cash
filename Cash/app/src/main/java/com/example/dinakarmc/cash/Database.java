package com.example.dinakarmc.cash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.LoginFilter;

/**
 * Created by dinakarmc on 9/1/16.
 */
public class Database extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Cash2.db";
    public static final String TABLE_NAME = "cc_table";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "Username";
    public static final String COL_3 = "Email";
    public static final String COL_4 = "Password";
    public static final String COL_5 = "Balance";



    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "( "+COL_1+" TEXT NOT NULL ,"+COL_2+" VARCHAR NOT NULL ,"+COL_3+" VARCHAR NOT NULL ,"+COL_4+" VARCHAR NOT NULL, "+COL_5+" VARCHAR NOT NULL );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Name, String Username, String Email, String Password, String Balance){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(COL_1, Name);
        c.put(COL_2, Username);
        c.put(COL_3, Email);
        c.put(COL_4, Password);
        c.put(COL_5, Balance);

        long result = db.insert(TABLE_NAME, null, c);
        if (result == -1)
            return false;
        else
            return true;


    }

    public Cursor getUsername(String username){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where Username = '"+username+"';", null); //fetch duplicate username
        return res;
    }

    public Cursor getEmail(String email){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where Email = '"+email+"';", null);  //fetch duplicate email
        return res;

    }

    public int deleteUsername(String del){

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Username = ?", new String[] {del}); //delete username

    }

    public boolean UpdateBal(String Balance, String username){


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues con = new ContentValues();
        con.put(COL_5, Balance);
        db.update(TABLE_NAME, con, "Username = ?", new String[]{ username }  );
        return true;

    }

    public Cursor getBal(String urname){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where Username ='"+urname+"';", null);
        return res;
    }
}
