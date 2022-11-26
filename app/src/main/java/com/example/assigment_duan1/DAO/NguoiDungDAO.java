package com.example.assigment_duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assigment_duan1.Database.DB_Helper;
import com.example.assigment_duan1.Model.NguoiDung;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    private SQLiteDatabase db;

    public NguoiDungDAO(Context context) {
        DB_Helper helper = new DB_Helper(context);
        db = helper.getWritableDatabase();
    }
    //Thêm người dùng
    public long insert (NguoiDung nguoiDung){
        ContentValues values = new ContentValues();
        values.put("username",nguoiDung.getUsername());
        values.put("password",nguoiDung.getPassword());
        values.put("tenNguoiDung",nguoiDung.getTenNguoiDung());
        values.put("email",nguoiDung.getEmail());
        values.put("sdt",nguoiDung.getSdt());
        values.put("namSinh", String.valueOf(nguoiDung.getNamSinh()));
        values.put("type",nguoiDung.getType());
        return db.insert("nguoiDung",null,values);
    }
    //update người dùng
    public int update(NguoiDung nguoiDung){
        ContentValues values = new ContentValues();
        values.put("username",nguoiDung.getUsername());
        values.put("password",nguoiDung.getPassword());
        values.put("tenNguoiDung",nguoiDung.getTenNguoiDung());
        values.put("email",nguoiDung.getEmail());
        values.put("sdt",nguoiDung.getSdt());
        values.put("namSinh", String.valueOf(nguoiDung.getNamSinh()));
        values.put("type",nguoiDung.getType());
        return db.update("nguoiDung",values,"username = ?",new String[]{nguoiDung.getUsername()});
    }
    //xóa người dùng
    public int delete(String id){
        return db.delete("nguoiDung","username = ?",new String[]{id});
    }

    //lấy toàn bộ danh sách người dùng
    public List<NguoiDung> getAll() throws ParseException {
        String sql = "SELECT * FROM nguoiDung";
        return getData(sql);
    }

    //lấy người dùng theo username
    public NguoiDung getUsername(String username) throws ParseException {
        String sql = "SELECT * FROM nguoiDung WHERE username = ?";
        List<NguoiDung> list = getData(sql,username);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<NguoiDung> getData(String sql, String ... selectionArgs) throws ParseException {
        List<NguoiDung> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            NguoiDung nguoiDung = new NguoiDung();
            nguoiDung.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            nguoiDung.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            nguoiDung.setTenNguoiDung(cursor.getString(cursor.getColumnIndex("tenNguoiDung")));
            nguoiDung.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            nguoiDung.setSdt(cursor.getString(cursor.getColumnIndex("sdt")));
            nguoiDung.setNamSinh(cursor.getString(cursor.getColumnIndex("namSinh")));
            nguoiDung.setType(Integer.parseInt(cursor.getString(cursor.getColumnIndex("type"))));
            list.add(nguoiDung);
        }
        return list;
    }

    //xây dựng hàm checklogin để kiểm tra tên đăng nhập và mật khẩu của nguoif dùng
    public int checkLLogin(String user, String pass) throws ParseException {
        String sql = "SELECT * FROM nguoiDung WHERE username = ? AND password = ?";
        List<NguoiDung> list = getData(sql,user,pass);
        if(list.size() == 0){
            return 0;
        }
        return 1;
    }
}
