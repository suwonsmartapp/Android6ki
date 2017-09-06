package com.example.dbsqliteexam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by junsuk on 2017. 9. 6..
 */

public class UserDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "User.db";

    public static final String SQL_CREATE_USER =
            "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    UserContract.UserEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    UserContract.UserEntry.COLUMN_NAME_PASSWORD + " TEXT" +
                    ");";

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // DB에 접근하는 최초 동작일 때
        sqLiteDatabase.execSQL(SQL_CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + UserContract.UserEntry.TABLE_NAME);
        sqLiteDatabase.execSQL(SQL_CREATE_USER);
    }
}
