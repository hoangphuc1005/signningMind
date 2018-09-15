package com.MentalHealth.mental.infonew.view;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.MentalHealth.mental.infonew.model.Data;

import java.util.ArrayList;
import java.util.List;

public class DBInformNew extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";

    // Phiên bản
    private static final int DATABASE_VERSION = 2;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "info_new_detail";

    public DBInformNew(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TABLE_USER = "InfoNewDetail";

    private static final String USER_ID = "id";
    private static final String USER_TITLE = "title";
    private static final String USER_IMAGE = "image";


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ...USER ");
        // Script tạo bảng.
        String script = "CREATE TABLE " + TABLE_USER + "("
                + USER_ID + " TEXT,"
                + USER_TITLE + " TEXT,"
                + USER_IMAGE + " TEXT"
                + ")";
        // Chạy lệnh tạo bảng.
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");

        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);


        // Và tạo lại.
        onCreate(db);
    }

    public void addUser(Data user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, user.getId());
        values.put(USER_TITLE, user.getTitle());
        values.put(USER_IMAGE, user.getImage());
//        values.put(USER_BIRTHDAY, user.getBirthday());
        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_USER, null, values);
        // Đóng kết nối database.
        db.close();
    }

    public Data getUser(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[]{USER_ID,
                        USER_TITLE, USER_IMAGE},
                USER_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Data user = new Data();
                assert cursor != null;
                user.setId(cursor.getInt(0));
                user.setTitle(cursor.getString(1));
                user.setImage(cursor.getString(2));

                if (!cursor.isClosed()) {
                    cursor.close();
                }
                db.close();
                return user;
            }
            // return user
        }
        return null;
    }


    public List<Data> getAllUsers() {
        Log.i(TAG, "MyDatabaseHelper.getAllusers ... ");

        List<Data> userList = new ArrayList<Data>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Data user = new Data();
                user.setId(cursor.getInt(0));
                user.setTitle(cursor.getString(1));
                user.setImage(cursor.getString(2));
                // Thêm vào danh sách.
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // return user list
        return userList;
    }

    public int getUsersCount() {
        Log.i(TAG, "MyDatabaseHelper.getusersCount ... ");

        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    public void updateUser(Data user) {
        SQLiteDatabase db = this.getWritableDatabase();
        int id = user.getId();
        String selection = USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        ContentValues values = new ContentValues();
        values.put(USER_TITLE, user.getTitle());
        values.put(USER_IMAGE, user.getImage());

        db.update(TABLE_USER, values, selection, selectionArgs);
    }

    public void deleteUserById(Data user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public void deleteAllUser() {
        Log.i(TAG, "MyDatabaseHelper.updateuser ... ");

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_USER);
        db.close();
    }
}