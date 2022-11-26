package com.example.assigment_duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assigment_duan1.Database.DB_Helper;
import com.example.assigment_duan1.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    private SQLiteDatabase db;

    public SanPhamDAO(Context context) {
        DB_Helper helper = new DB_Helper(context);
        db = helper.getWritableDatabase();
    }

    //Tạo phương thức insert
    public long insert(SanPham sanPham){
        ContentValues values = new ContentValues();
        values.put("tenSanPham",sanPham.getTenSanPham());
        values.put("soLuong",sanPham.getSoLuong());
        values.put("donGia",sanPham.getDonGia());
        values.put("loaiSanPham",sanPham.getLoaiSanPham());
        return db.insert("sanPham",null,values);
    }

    //Tạo phương thức update
    public int update(SanPham sanPham){
        ContentValues values = new ContentValues();
        values.put("tenSanPham",sanPham.getTenSanPham());
        values.put("soLuong",sanPham.getSoLuong());
        values.put("donGia",sanPham.getDonGia());
        values.put("loaiSanPham",sanPham.getLoaiSanPham());
        return db.update("sanPham",values,"maSanPham = ?",new String[]{String.valueOf(sanPham.getMaSanPham())});
    }

    //Tạo phương thức dalete
    public int delete(String id){
        return db.delete("sanPham","maSanPham = ?",new String[]{id});
    }

    //Phương thức lấy tất cả dữ liệu sản phảm
    public List<SanPham> getall(){
        String sql = "SELECT * FROM sanPham";
        return getData(sql);
    }

    //Phương thức lấy sản phẩm theo id
    public SanPham getid(String id){
        String sql = "SELECT * FROM sanPham WHERE maSanPham = ?";
        List<SanPham > list = getData(sql,id);
        return list.get(0);
    }

    //Phương thức lấy sản phẩm theo tên loại
    public List<SanPham> getByType(String name){
        String sql = "SELECT * FROM sanPham WHERE loaiSanPham = ?";
        return getData(sql,name);
    }

    //Phương thức lấy dữ liệu
    @SuppressLint("Range")
    private List<SanPham> getData(String sql, String ... selectionArgs){
        List<SanPham> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            SanPham sanPham = new SanPham();
            sanPham.setMaSanPham(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSanPham"))));
            sanPham.setTenSanPham(cursor.getString(cursor.getColumnIndex("tenSanPham")));
            sanPham.setDonGia(Double.parseDouble(cursor.getString(cursor.getColumnIndex("donGia"))));
            sanPham.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            sanPham.setLoaiSanPham(cursor.getString(cursor.getColumnIndex("loaiSanPham")));
            list.add(sanPham);
        }
        return list;
    }
}
