package com.arti.demosqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Mydatabase extends SQLiteOpenHelper {
    private  static final  String DATABASE_NAME="student.db";
    private  static final  String TABLE_NAME="student";
    private  static final  String COL_1="ID";
    private  static final  String COL_2="NAME";
    private  static final  String COL_3="SURNAME";
    private  static final  String COL_4="ROLL";

    public Mydatabase(Context context) {
        super(context,
                DATABASE_NAME,
                null,
                1);
        //it is used for create a table and coulmn

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      sqLiteDatabase.execSQL("create table "+TABLE_NAME+" " +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,ROLL TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }
    public boolean isInsert(String name,String sur_name,String roll){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,sur_name);
        contentValues.put(COL_4,roll);
        long res=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if (res==-1){
            return false;
        }
        else {
            return  true;
        }

    }

    public Cursor readData()
    {

        SQLiteDatabase database=this.getReadableDatabase();
        Cursor result=database.rawQuery("select * from "+TABLE_NAME,null);
        return  result;
    }
    public  boolean isUpdate(String id,String name,String sur_name,String roll){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,sur_name);
        contentValues.put(COL_4,roll);
        sqLiteDatabase.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
        return true;
    }
    public int deleteData( String id){
        SQLiteDatabase database=this.getWritableDatabase();
        return database.delete(TABLE_NAME,"ID=?",new String[]{id});

    }
}
