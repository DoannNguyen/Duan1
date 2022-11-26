package com.example.assigment_duan1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB_Helper extends SQLiteOpenHelper {

    private static String DB_NAME = "QL_Market";
    private static int DB_VERSION = 2;
    public DB_Helper(@Nullable Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableNguoiDung = "CREATE TABLE nguoiDung(username text not null primary key," +
                "password text not null," +
                "tenNguoiDung text not null," +
                "email text not null," +
                "sdt text not null," +
                "namSinh text not null," +
                "type integer not null)";
        db.execSQL(tableNguoiDung);
        String tableHoaDon = "CREATE TABLE hoaDon(maHoaDon integer not null primary key autoincrement ," +
                "nguoiTao text not null, maSanPham integer not null," +
                "tenSanPham text not null," +
                "donGia double not null," +
                "ngayTao date not null," +
                "tenNguoiTao text not null," +
                "FOREIGN KEY(nguoiTao) REFERENCES nguoiDung(username)," +
                "FOREIGN KEY(maSanPham) REFERENCES sanPham(maSanPham))";
        db.execSQL(tableHoaDon);
        String tableSanPham = "CREATE TABLE sanPham(maSanPham integer not null primary key autoincrement," +
                "tenSanPham text not null," +
                "soLuong integer not null, " +
                "donGia double not null," +
                "loaiSanPham text not null)";
        db.execSQL(tableSanPham);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String tableNguoiDung = "DROP TABLE IF EXISTS nguoiDung";
        db.execSQL(tableNguoiDung);
        String tableHoaDon = "DROP TABLE IF EXISTS hoaDon";
        db.execSQL(tableHoaDon);
        String tableSanPham = "DROP TABLE IF EXISTS sanPham";
        db.execSQL(tableSanPham);
        onCreate(db);
    }
}
