package com.example.app_phone_store_manager_nhom_3.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app_phone_store_manager_nhom_3.database.DbHelper;
import com.example.app_phone_store_manager_nhom_3.model.Hang;
import com.example.app_phone_store_manager_nhom_3.model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class DaoKhachHang {
    private SQLiteDatabase database;
    private DbHelper dbHelper;

    public DaoKhachHang(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addKH(KhachHang khachHang) {
        ContentValues values = new ContentValues();
        values.put(KhachHang.TB_COL_ID, khachHang.getMaKH());
        values.put(KhachHang.TB_COL_NAME, khachHang.getHoTen());
        values.put(KhachHang.TB_COL_PHONE, khachHang.getDienThoai());
        values.put(KhachHang.TB_COL_LOCALE, khachHang.getDiaChi());
        return database.insert(KhachHang.TB_NAME, null, values);
    }

    public int updateKH(KhachHang khachHang, String maOld) {
        ContentValues values = new ContentValues();
        values.put(KhachHang.TB_COL_ID, khachHang.getMaKH());
        values.put(KhachHang.TB_COL_NAME, khachHang.getHoTen());
        values.put(KhachHang.TB_COL_PHONE, khachHang.getDienThoai());
        values.put(KhachHang.TB_COL_LOCALE, khachHang.getDiaChi());
        return database.update(KhachHang.TB_NAME, values, "maKH = ? ", new String[]{maOld});
    }

    public int deleteKH(KhachHang khachHang) {
        return database.delete(KhachHang.TB_NAME, "maKH = ? ", new String[]{khachHang.getMaKH()});
    }

    public List<KhachHang> getAll() {
        String sql = "SELECT * FROM KhachHang";
        List<KhachHang> list = getData(sql);
        return list;
    }

    public KhachHang getMaKH(String maKH){
        String sql = "SELECT * FROM KhachHang WHERE maKH = ?";
        List<KhachHang> list = getData(sql, maKH);
        return  list.get(0);
    }
    public List<KhachHang> getAllSXTenKH() {
        String sql = "SELECT * FROM KhachHang ORDER BY hoTen ASC";
        List<KhachHang> list = getData(sql);
        return list;
    }
    public List<KhachHang> getAllSXMaKH() {
        String sql = "SELECT * FROM KhachHang ORDER BY maKH ASC";
        List<KhachHang> list = getData(sql);
        return list;
    }

    public List<KhachHang> getData(String sql, String... args) {
        List<KhachHang> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, args);
        while (cursor.moveToNext()) {
            KhachHang khachHang = new KhachHang();
            khachHang.setMaKH(cursor.getString(cursor.getColumnIndex(KhachHang.TB_COL_ID)));
            khachHang.setHoTen(cursor.getString(cursor.getColumnIndex(KhachHang.TB_COL_NAME)));
            khachHang.setDienThoai(cursor.getString(cursor.getColumnIndex(KhachHang.TB_COL_PHONE)));
            khachHang.setDiaChi(cursor.getString(cursor.getColumnIndex(KhachHang.TB_COL_LOCALE)));
            list.add(khachHang);
        }
        return list;
    }
}
