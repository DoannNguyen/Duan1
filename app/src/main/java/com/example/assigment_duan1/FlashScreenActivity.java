package com.example.assigment_duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assigment_duan1.DAO.NguoiDungDAO;

import java.text.ParseException;

public class FlashScreenActivity extends AppCompatActivity {

    private Dialog dialog;
    private TextView mTextView;
    private EditText edUsername, edPassword;
    private Button btnLogin, btnCancel;
    private NguoiDungDAO mDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
        // ánh xạ dialog và thành phần trong dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.login_dialog);
        mTextView = dialog.findViewById(R.id.tvCreateAccount);
        edUsername = dialog.findViewById(R.id.edUsername);
        edPassword = dialog.findViewById(R.id.edPassword);
        btnLogin = dialog.findViewById(R.id.btnLogin);
        btnCancel = dialog.findViewById(R.id.btnCancel);
        mDAO = new NguoiDungDAO(this);

        //Lấy dữ liệu đã đăng nhập trước đó
        getDataUser();

        //Xửa lý nut login-nếu dữ liệu trống sẽ đưa ra thông báo "trống"
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edUsername.getText().toString().equals("")){
                    Toast.makeText(FlashScreenActivity.this, "Nhập đầy đủ Username!", Toast.LENGTH_SHORT).show();
                }else if(edPassword.getText().toString().equals("")){
                    Toast.makeText(FlashScreenActivity.this, "Nhập đầy đủ Password!", Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(FlashScreenActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    try {
                        if(mDAO.checkLLogin(edUsername.getText().toString(), edPassword.getText().toString()) > 0){
                            Toast.makeText(FlashScreenActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                            save();

                            //đóng gói dữ liệu truyền qua main
                            Intent intent = new Intent(FlashScreenActivity.this,MainActivity.class);
                            intent.putExtra("email",mDAO.getUsername(edUsername.getText().toString()).getEmail());
                            intent.putExtra("type",mDAO.getUsername(edUsername.getText().toString()).getType());
                            intent.putExtra("username",mDAO.getUsername(edUsername.getText().toString()).getUsername());
                            startActivity(intent);
                        }else{
                            Toast.makeText(FlashScreenActivity.this, "Tên đăng nhập hoặc mật khẩu không dúng!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                        e.getMessage();
                    }
                }
            }
        });

        //Xử lý nút cancel - đóng dialog và ứng dụng
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });

        //Xử lý phần tạo tài khoản admin
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FlashScreenActivity.this,CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.show();
            }
        },1500);
    }

    //Lưu dữ liệu vào bộ nhớ máy
    private void save() throws ParseException {
        SharedPreferences preferences = getSharedPreferences("DataUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username",edUsername.getText().toString());
        editor.putString("pass",edPassword.getText().toString());
        editor.commit();
    }
    public void getDataUser(){
        SharedPreferences preferences = getSharedPreferences("DataUser",Context.MODE_PRIVATE);
        String username = preferences.getString("username","");
        //String password = preferences.getString("pass","");
        edUsername.setText(username);
        //edPassword.setText(password);
    }
}