package com.example.duanmau.dao;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.dbHelper;
import com.example.duanmau.model.LoaiSach;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;

public class daoSach {
    private final dbHelper dbHelper;

    public daoSach(Context context) {
        dbHelper = new dbHelper(context);
    }
    public ArrayList<Sach> selectAll() {
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor =db.rawQuery("SELECT * FROM tb_Sach INNER JOIN tb_LoaiSach ON tb_Sach.maLoai = tb_LoaiSach.maLoai", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Sach sach = new Sach();
                    sach.setMaSach(cursor.getInt(0));
                    sach.setTenSach(cursor.getString(1));
                    sach.setGiaThue(cursor.getInt(2));
                    sach.setMaLoai(cursor.getInt(3));
                    sach.setTenLoai(cursor.getString(5));
                    list.add(sach);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return list;
    }
    public boolean insert(Sach sach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenSach", sach.getTenSach());
        values.put("giaThue", sach.getGiaThue());
        values.put("maLoai", sach.getMaLoai());
        long row = db.insert("tb_Sach", null, values);
        return (row > 0);
    }
    public boolean delete(int maSach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("tb_Sach", "maSach=?", new String[]{String.valueOf(maSach)});
        return (row > 0);
    }
}
