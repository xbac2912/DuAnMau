package com.example.duanmau.dao;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.dbHelper;
import com.example.duanmau.model.ThanhVien;
import com.example.duanmau.model.ThuThu;

import java.util.ArrayList;

public class daoThanhVien {
    private final dbHelper dbHelper;

    public daoThanhVien(Context context) {
        dbHelper = new dbHelper(context);
    }
    public ArrayList<ThanhVien> selectAll() {
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor =db.rawQuery("SELECT * FROM tb_ThanhVien", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    ThanhVien tv = new ThanhVien();
                    tv.setMaTV(cursor.getInt(0));
                    tv.setTenTV(cursor.getString(1));
                    tv.setNamSinh(cursor.getString(2));
                    list.add(tv);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return list;
    }
    public boolean insert(ThanhVien tv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenTV", tv.getTenTV());
        values.put("namSinh", tv.getNamSinh());
        long row = db.insert("tb_ThanhVien", null, values);
        return (row > 0);
    }
    public boolean update(ThanhVien tv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenTV", tv.getTenTV());
        values.put("namSinh", tv.getNamSinh());
        long row = db.update("tb_ThanhVien", values, "maTV = ?", new String[]{String.valueOf(tv.getMaTV())});
        return (row > 0);
    }
    public boolean delete(int maTV) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("tb_ThanhVien", "maTV=?", new String[]{String.valueOf(maTV)});
        return (row > 0);
    }
}
