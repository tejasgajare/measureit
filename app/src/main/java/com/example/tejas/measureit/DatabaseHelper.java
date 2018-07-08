package com.example.tejas.measureit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String TAG = "MyAdapter";

    private static final String DATABASE_NAME = "Projects.db";

    private static final String PROJECT_TABLENAME = "project_table";
    private static final String PROJECT_ID = "ID";
    private static final String PROJECT_TITLE = "TITLE";
    private static final String PROJECT_DESCRIPTION = "DESCRIPTION";
    private static final String PROJECT_MEASUREMENTS = "MEASUREMENTS";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+PROJECT_TABLENAME+"(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "TITLE TEXT," +
                "DESCRIPTION TEXT," +
                "MEASUREMENTS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+PROJECT_TABLENAME);
        onCreate(db);
    }

    public boolean insertData(String title, String description, int measurement){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROJECT_TITLE, title);
        contentValues.put(PROJECT_DESCRIPTION, description);
        contentValues.put(PROJECT_MEASUREMENTS, measurement);

        long result = db.insert(PROJECT_TABLENAME, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor selectData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+PROJECT_TABLENAME, null);
        return res;
    }

    public boolean deleteRow(String title) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(PROJECT_TABLENAME, "TITLE" + "=" + '"'+title+'"', null);

        if(result == -1){
            return false;
        } else {
            return true;
        }

    }
}
