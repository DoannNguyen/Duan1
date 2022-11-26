package com.example.assigment_duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assigment_duan1.DAO.NguoiDungDAO;
import com.example.assigment_duan1.Model.NguoiDung;

import java.text.SimpleDateFormat;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText edUsername, edPassword, edHoTen, edEmail, edSdt, edNgay, edRePassword, edMaBV;
    private Button btnTaoTK, btnHuyTTK;
    private NguoiDung mNguoiDung;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
    private NguoiDungDAO dao ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //ánh xạ các thành phần
        edUsername = findViewById(R.id.edUsernameCR);
        edPassword = findViewById(R.id.edPasswordCR);
        edRePassword = findViewById(R.id.edRePasswordCR);
        edHoTen = findViewById(R.id.edHoten);
        edEmail = findViewById(R.id.edEmail);
        edSdt = findViewById(R.id.edSdt);
        edNgay = findViewById(R.id.edNgaySinh);
        edMaBV = findViewById(R.id.edMaBV);
        btnTaoTK = findViewById(R.id.btnTaoTK);
        btnHuyTTK = findViewById(R.id.btnHuyTTK);
        dao = new NguoiDungDAO(this);

        //sét sự kiện cho btnTaoTK
        btnTaoTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNguoiDung = new NguoiDung();
                mNguoiDung.setUsername(edUsername.getText().toString());
                mNguoiDung.setPassword(edPassword.getText().toString());
                mNguoiDung.setTenNguoiDung(edHoTen.getText().toString());
                mNguoiDung.setEmail(edEmail.getText().toString());
                mNguoiDung.setSdt(edSdt.getText().toString());
                mNguoiDung.setNamSinh(edNgay.getText().toString());
                if(edMaBV.getText().toString().equals("admin")){
                    mNguoiDung.setType(0);
                }else{
                    mNguoiDung.setType(1);
                }
                if(edPassword.getText().toString().equals(edRePassword.getText().toString())){
                    if(dao.insert(mNguoiDung) > 0){
                        Toast.makeText(CreateAccountActivity.this, "Thêm người dùng thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(CreateAccountActivity.this, "Thêm người dùng thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CreateAccountActivity.this,"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}