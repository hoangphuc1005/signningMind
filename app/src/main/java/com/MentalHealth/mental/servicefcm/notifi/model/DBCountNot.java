package com.MentalHealth.mental.servicefcm.notifi.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBCountNot extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";

    // Phiên bản
    private static final int DATABASE_VERSION = 2;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "DBCountNot";

    public DBCountNot(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TABLE_USER = "Not_detail";

    private static final String USER_ID = "id";


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ...USER ");
        // Script tạo bảng.
        String script = "CREATE TABLE " + TABLE_USER + "("
                + USER_ID + " TEXT"
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

    public void addUser(NotModel user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, user.getCount());
//        values.put(USER_BIRTHDAY, user.getBirthday());
        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_USER, null, values);
        // Đóng kết nối database.
        db.close();
    }

    public NotModel getUser() {
        String selectQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                NotModel user = new NotModel();
                assert cursor != null;
                user.setCount(cursor.getInt(0));

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

    public void updateUser(NotModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        int id = user.getCount();
        String selection = USER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        ContentValues values = new ContentValues();
        values.put(USER_ID,user.getCount());
        db.update(TABLE_USER, values, selection, selectionArgs);
    }

    public void deleteUserById(NotModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, USER_ID + " = ?",
                new String[]{String.valueOf(user.getCount())});
        db.close();
    }

    public void deleteAllUser() {
        Log.i(TAG, "MyDatabaseHelper.updateuser ... ");

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_USER);
        db.close();
    }
}
