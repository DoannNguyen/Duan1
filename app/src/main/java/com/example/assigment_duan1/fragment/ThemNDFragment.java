package com.example.assigment_duan1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assigment_duan1.DAO.NguoiDungDAO;
import com.example.assigment_duan1.Model.NguoiDung;
import com.example.assigment_duan1.R;
import com.google.android.material.textfield.TextInputLayout;


public class ThemNDFragment extends Fragment {

    private EditText edUser, edPass, edRepass, edName, edMail, edPhone, edDate, edProtect;
    TextInputLayout til;
    private Button btnSave, btnCancel;
    private NguoiDungDAO mDAO;

    public ThemNDFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_them_n_d, container, false);

        //Ánh xạ các thành phần
        edUser = view.findViewById(R.id.edUser);
        edPass = view.findViewById(R.id.edPas);
        edRepass = view.findViewById(R.id.edRepass);
        edName = view.findViewById(R.id.edName);
        edMail = view.findViewById(R.id.edMail);
        edPhone = view.findViewById(R.id.edPhone);
        edDate = view.findViewById(R.id.edDate);
        edProtect = view.findViewById(R.id.edProtect);
        btnSave = view.findViewById(R.id.btnCrete);
        btnCancel = view.findViewById(R.id.btnDele);
        til = view.findViewById(R.id.til);
        til.setVisibility(View.INVISIBLE);

        //kiểm tra đối tượng đăng nhập
        Intent intent = getActivity().getIntent();
        int i = intent.getIntExtra("type",1);
        Toast.makeText(getActivity(), ""+i, Toast.LENGTH_SHORT).show();

        //Tạo nguoidungDAO
        mDAO = new NguoiDungDAO(getActivity());

        //sét sự kiệ cho nút save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edUser.getText().toString().equals("") ||
                edPass.getText().toString().equals("") ||
                edName.getText().toString().equals("") ||
                edMail.getText().toString().equals("") ||
                edPhone.getText().toString().equals("") ||
                edDate.getText().toString().equals("") ){
                    Toast.makeText(getActivity(), "Hãy nhập đầy đủ dữ liệu", Toast.LENGTH_SHORT).show();
                }
                if(edPass.getText().toString().equals(edRepass.getText().toString())){
                    NguoiDung nguoiDung = new NguoiDung();
                    nguoiDung.setUsername(edUser.getText().toString());
                    nguoiDung.setPassword(edPass.getText().toString());
                    nguoiDung.setTenNguoiDung(edName.getText().toString());
                    nguoiDung.setEmail(edMail.getText().toString());
                    nguoiDung.setSdt(edPhone.getText().toString());
                    nguoiDung.setNamSinh(edDate.getText().toString());
                    if(edProtect.getText().toString().equals("admin")){
                        nguoiDung.setType(0);
                    }else{
                        nguoiDung.setType(1);
                    }
                    if(mDAO.insert(nguoiDung) > 0){
                        Toast.makeText(getActivity(), "Thêm người dùng thành công", Toast.LENGTH_SHORT).show();
                        edUser.setText("");
                        edPass.setText("");
                        edRepass.setText("");
                        edName.setText("");
                        edMail.setText("");
                        edPhone.setText("");
                        edDate.setText("");
                        edProtect.setText("");
                    }else{
                        Toast.makeText(getActivity(), "Lỗi thêm gười dùng", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getActivity(), "Mật khẩu không đúng !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //sét dự kiện cho nút cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUser.setText("");
                edPass.setText("");
                edRepass.setText("");
                edName.setText("");
                edMail.setText("");
                edPhone.setText("");
                edDate.setText("");
                edProtect.setText("");
            }
        });

        return view;
    }
}