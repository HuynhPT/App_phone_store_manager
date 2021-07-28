package com.example.app_phone_store_manager_nhom_3.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.app_phone_store_manager_nhom_3.database.DbHelper;
import com.example.app_phone_store_manager_nhom_3.model.NhanVien;

public class DaoNhanVien {
    SQLiteDatabase database;
    DbHelper dbHelper;

    public DaoNhanVien(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void openNV() {
        database = dbHelper.getWritableDatabase();
    }

    public void closeNV() {
        dbHelper.close();
    }
//    public long addNV(NhanVien nhanVien){
//
//    }
}
