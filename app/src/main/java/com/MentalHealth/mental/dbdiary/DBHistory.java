package com.MentalHealth.mental.dbdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.MentalHealth.mental.diary.model.DiaryModel;

import java.util.ArrayList;
import java.util.List;


public class DBHistory extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";

    // Phiên bản
    private static final int DATABASE_VERSION = 2;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "signing_mine";

    public DBHistory(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TABLE_DIARY = "diary";

    private static final String DIARY_ID = "id";
    private static final String DIARY_DATE = "date";
    private static final String DIARY_TITLE = "title";
    private static final String DIARY_CONTENT = "content";

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
//        Toast.makeText(context,"db tao thanh cong",Toast.LENGTH_LONG).show();
        // Script tạo bảng.
        String script = "CREATE TABLE " + TABLE_DIARY + "("
                + DIARY_ID + " TEXT," + DIARY_DATE + " TEXT,"
                + DIARY_TITLE + " TEXT,"
                + DIARY_CONTENT + " TEXT" + ")";
        // Chạy lệnh tạo bảng.
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            // copyDatabase();
            Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");

        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIARY);


        // Và tạo lại.
        onCreate(db);
    }

    public void addDiary(DiaryModel diary) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DIARY_ID, diary.getId());
        values.put(DIARY_DATE, diary.getDateOfDiary());
        values.put(DIARY_TITLE, diary.getTitleOfDiary());
        values.put(DIARY_CONTENT, diary.getContentOfDiary());

        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_DIARY, null, values);

        // Đóng kết nối database.
        db.close();
    }

    public DiaryModel getDiary(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DIARY, new String[]{DIARY_ID,
                        DIARY_DATE, DIARY_TITLE, DIARY_CONTENT},
                DIARY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                DiaryModel diaryModel = new DiaryModel();
                diaryModel.setId(cursor.getString(0));
                diaryModel.setDateOfDiary(cursor.getString(1));
                diaryModel.setTitleOfDiary(cursor.getString(2));
                diaryModel.setContentOfDiary(cursor.getString(3));

                if (!cursor.isClosed()) {
                    cursor.close();
                }
                db.close();
                // return user
                return diaryModel;
            }
        }
        return null;
    }


    public List<DiaryModel> getAllDiary() {
        Log.i(TAG, "MyDatabaseHelper.getAllusers ... ");
        List<DiaryModel> diaryModelList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DIARY + " ORDER BY " + DIARY_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    DiaryModel diaryModel = new DiaryModel();
                    diaryModel.setId(cursor.getString(0));
                    diaryModel.setDateOfDiary(cursor.getString(1));
                    diaryModel.setTitleOfDiary(cursor.getString(2));
                    diaryModel.setContentOfDiary(cursor.getString(3));
                    diaryModelList.add(diaryModel);
                } while (cursor.moveToNext());
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            db.close();
        }
        // return user list
        return diaryModelList;
    }


    public int updateDiary(DiaryModel diaryModel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DIARY_DATE, diaryModel.getDateOfDiary());
        values.put(DIARY_TITLE, diaryModel.getTitleOfDiary());
        values.put(DIARY_CONTENT, diaryModel.getContentOfDiary());

        // updating row
        return db.update(TABLE_DIARY, values, DIARY_ID + " = ?",
                new String[]{String.valueOf(diaryModel.getId())});
    }

    public void deleteAllUserHis() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete  from " + TABLE_DIARY);
        db.close();
    }

    public void deleteDiary(DiaryModel diaryModel) {
        Log.i(TAG, "MyDatabaseHelper.updateuser ... " + diaryModel.getId());

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DIARY, DIARY_CONTENT + " = ?",
                new String[]{String.valueOf(diaryModel.getContentOfDiary())});
        // db.delete(TABLE_DIARY,null,null);
        //db.execSQL("delete  from "+ TABLE_DIARY);
        db.close();
    }
}
