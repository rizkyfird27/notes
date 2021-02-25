package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ulangan.db";
    private static final int DB_VERSION = 1;
    private static String TABLE_NAME = "content";
    static final String _ID = "_id";
    static final String TITLE = "judul";
    static final String DESC = "deskripsi";
    static final String DATE = "date";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createContentTable = "CREATE TABLE " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY," + TITLE + " TEXT NOT NULL," + DESC + " TEXT NOT NULL," + DATE + " TEXT NOT NULL" + ")";
        db.execSQL(createContentTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insert(Content content){
        SQLiteDatabase db = getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        ContentValues values = new ContentValues();
        values.put(TITLE, content.getTitle());
        values.put(DESC, content.getDesc());
        values.put(DATE, dateFormat.format(date));
        db.insert(TABLE_NAME, null, values);
    }

    public List<Content> selectContentList(){
        ArrayList<Content> contentList = new ArrayList<Content>();

        SQLiteDatabase db = getReadableDatabase();
        String[] columns = { _ID, TITLE, DESC, DATE };

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String desc = cursor.getString(2);
            String date = cursor.getString(3);

            Content content = new Content();
            content.setId(id);
            content.setTitle(title);
            content.setDesc(desc);
            content.setDate(date);

            contentList.add(content);
        }
        return contentList;
    }

    public void update(Content content){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        values.put(TITLE, content.getTitle());
        values.put(DESC, content.getDesc());
        values.put(DATE, dateFormat.format(date));
        String whereClause = _ID + " = ' " + content.getId() + "'";
        db.update(TABLE_NAME, values, whereClause, null);
    }

    public void delete(int ID){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = _ID + "= '" + ID + "'";

        db.delete(TABLE_NAME, whereClause, null);
    }

}
