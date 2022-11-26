package com.example.assigment_duan1.fragment;

import static java.time.LocalDate.now;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assigment_duan1.Adapter.HoaDonAdapter;
import com.example.assigment_duan1.Adapter.SanPhamSpinnerAdapter;
import com.example.assigment_duan1.DAO.HoaDonDAO;
import com.example.assigment_duan1.DAO.NguoiDungDAO;
import com.example.assigment_duan1.DAO.SanPhamDAO;
import com.example.assigment_duan1.Model.HoaDon;
import com.example.assigment_duan1.Model.NguoiDung;
import com.example.assigment_duan1.Model.SanPham;
import com.example.assigment_duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class HoaDonFragment extends Fragment {

    Dialog mDialog;
    RecyclerView mRecyclerView;
    FloatingActionButton fab;;
    Spinner mSpinner;
    EditText edSoLuong;
    TextView tvPrice;
    Button btnThanhToan, btnHuyBo;
    List<SanPham> mList;
    SanPhamSpinnerAdapter adapter;
    SanPhamDAO mDAO;
    NguoiDungDAO mNguoiDungDAO;
    int maSP ;
    String tenSP, tenNguoiTao, maNguoiTao;
    double donGiaTT, donGia ;
    HoaDonDAO mHoaDonDAO;
    HoaDonAdapter mHoaDonAdapter;

    public HoaDonFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);

        //ánh xạ các thành phần
        mRecyclerView = view.findViewById(R.id.rcv_hoaDon);
        fab = view.findViewById(R.id.fb_hoaDon);

        //ánh xạ dialog
        mDialog = new Dialog(getActivity());
        mDialog.setContentView(R.layout.add_bill_dialog);
        mSpinner = mDialog.findViewById(R.id.spSanPham);
        edSoLuong = mDialog.findViewById(R.id.edsoluong);
        tvPrice = mDialog.findViewById(R.id.tvPrice);
        btnThanhToan = mDialog.findViewById(R.id.btnThanhToan);
        btnHuyBo = mDialog.findViewById(R.id.btnHuyBo);

        // dổ dữ liệu sản phẩm vào spinner
        mDAO = new SanPhamDAO(getActivity());
        mList = mDAO.getall();
        adapter = new SanPhamSpinnerAdapter(getActivity(),mList);
        mSpinner.setAdapter(adapter);


        //Lấy dữ liệu từ spinner
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSP = mList.get(position).getMaSanPham();
                tenSP = mList.get(position).getTenSanPham();
                donGiaTT = mList.get(position).getDonGia();
                if(edSoLuong.getText().toString().equals("")){
                    edSoLuong.setText(0+"");
                }else{
                    donGia = donGiaTT*(Double.parseDouble(edSoLuong.getText().toString()));
                    tvPrice.setText(String.valueOf(donGia));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Lấy dữ liệu người tạo
        mNguoiDungDAO = new NguoiDungDAO(getActivity());
        Intent intent = getActivity().getIntent();
        String data = intent.getStringExtra("username");
        try {
            NguoiDung nguoiDung = mNguoiDungDAO.getUsername(data);
            tenNguoiTao = nguoiDung.getTenNguoiDung();
            maNguoiTao = nguoiDung.getUsername();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //sét dự kiện cho float button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.show();
            }
        });

        //sét dữ liệu textview khi thay dổi giá trị editext
        edSoLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tvPrice.setText(0+"");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(edSoLuong.getText().toString().equals("")){
                    edSoLuong.setText(0+"");
                }else{
                    donGia = donGiaTT*(Double.parseDouble(edSoLuong.getText().toString()));
                    tvPrice.setText(String.valueOf(donGia));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mHoaDonDAO = new HoaDonDAO(getActivity());
        capnhatdulieu();

        //sét sự kiện cho nut thanh toán
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edSoLuong.getText().toString().equals(0+"")){
                    Toast.makeText(getActivity(), "Số lượng phải lớn hơn 0 !", Toast.LENGTH_SHORT).show();
                }else{
                    HoaDon hoaDon = new HoaDon();
                    hoaDon.setMaSanPham(maSP);
                    hoaDon.setDonGia(donGia);
                    hoaDon.setNgay(Date.valueOf(String.valueOf(now())));
                    hoaDon.setNguoiTao(data);
                    hoaDon.setTenNguoiTao(tenNguoiTao);
                    hoaDon.setTenSanPham(tenSP);
                    SanPham sanPham = mDAO.getid(String.valueOf(maSP));
                    //Kiêm tra số lượng hàng trong kho so vs số lượng nhập vào
                    if(Integer.parseInt(edSoLuong.getText().toString()) > sanPham.getSoLuong()){
                        Toast.makeText(getActivity(), "số lượng hàng trong kho không đủ!", Toast.LENGTH_SHORT).show();
                    }else{
                        int soLuong = sanPham.getSoLuong() - Integer.parseInt(edSoLuong.getText().toString());
                        sanPham.setSoLuong(soLuong);
                        if(mHoaDonDAO.insert(hoaDon) > 0){
                            Toast.makeText(getActivity(), "Thêm thành công !", Toast.LENGTH_SHORT).show();

                            //cập nhật lại số lượng trong kho
                            mDAO.update(sanPham);
                            //Toast.makeText(getActivity(), "Đã cập nhật số lượng", Toast.LENGTH_SHORT).show();
                            capnhatdulieu();
                            edSoLuong.setText(0+"");
                            mDialog.dismiss();
                        }else{
                            Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        //sét dự kiện cho nút hủy
        btnHuyBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edSoLuong.setText("");
                mDialog.dismiss();
            }
        });
        return view;
    }

    //cập nhật tất cả hóa đơn
    private void capnhatdulieu(){
        List<HoaDon> list = mHoaDonDAO.getAll();
        mHoaDonAdapter = new HoaDonAdapter(list,getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mHoaDonAdapter);
    }
}