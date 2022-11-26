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

import java.text.ParseException;


public class DoiMKFragment extends Fragment {

    EditText edOldPass, edNewPass, edRePass;
    Button btnSave, btnCancel;
    NguoiDungDAO mDAO;

    public DoiMKFragment() {
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
        View view = inflater.inflate(R.layout.fragment_doi_m_k, container, false);

        //Ánh xạ các thành phần
        edOldPass = view.findViewById(R.id.edOld_pass);
        edNewPass = view.findViewById(R.id.edNew_pass);
        edRePass = view.findViewById(R.id.edRe_pass);
        btnSave = view.findViewById(R.id.btnLuu);
        btnCancel = view.findViewById(R.id.btnHuy);

        mDAO = new NguoiDungDAO(getActivity());

        //Sự kiện nút save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getActivity().getIntent();
                String name = intent.getStringExtra("username");

                try {
                    NguoiDung nguoiDung = mDAO.getUsername(name);
                    nguoiDung.setPassword(edNewPass.getText().toString());
                    if(mDAO.getUsername(name).getPassword().equals(edOldPass.getText().toString())){
                        if(edNewPass.getText().length() > 0 && edNewPass.getText().toString().equals(edRePass.getText().toString())){
                            if(mDAO.update(nguoiDung) > 0){
                                Toast.makeText(getActivity(), "Cập nhật mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                                edOldPass.setText("");
                                edNewPass.setText("");
                                edRePass.setText("");
                            }else{
                                Toast.makeText(getActivity(), "Lỗi cập nhật !!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getActivity(), "Nhâp đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getActivity(), "Mật khẩu sai!", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        //sự kiện nút cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edOldPass.setText("");
                edNewPass.setText("");
                edRePass.setText("");
            }
        });
        return view;
    }
}