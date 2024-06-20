package com.example.muslimpexam;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Students.USER", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE students (\n" +
                "  id INTEGER PRIMARY KEY,\n" +
                "  names TEXT NOT NULL,\n" +
                "  department TEXT NOT NULL,\n" +
                "  projectName TEXT NOT NULL,\n" +
                "  college TEXT NOT NULL\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Students");
    }

    public boolean insertuserdata(String id, String names, String department, String college, String projectName) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("names", names);
        contentValues.put("department", department);
        contentValues.put("projectName", projectName);
        contentValues.put("college", college);

        try {
            contentValues.put("id", Integer.parseInt(id)); // Convert id to integer if it's an integer field
        } catch (NumberFormatException e) {
            // Handle potential parsing error (if id is not a valid integer)
            return false;
        }

        long result = DB.insert("Students", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }


    public boolean updateuserdata(String id, String names, String department, String college, String projectName) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("names", names);
        contentValues.put("department", department);
        contentValues.put("projectName", projectName);
        contentValues.put("college", college);


        Cursor cursor = DB.rawQuery("Select * from Students where id= ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.update("Students", contentValues, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public boolean deletedata(String id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Students where id= ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Students", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }


    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Students", null);
        return cursor;
    }

}