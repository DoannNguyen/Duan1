package com.example.assigment_duan1.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assigment_duan1.Adapter.SanPhamAdapter;
import com.example.assigment_duan1.Adapter.SpinnerAdapter;
import com.example.assigment_duan1.DAO.SanPhamDAO;
import com.example.assigment_duan1.Model.SanPham;
import com.example.assigment_duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class SnacksFragment extends Fragment {

    RecyclerView mRecyclerView;
    Dialog mDialog;
    FloatingActionButton mFab;
    Spinner mSpinner;
    EditText edTenSanPham, edSoLuong, edDonGia;
    Button btnSave, btnCancel;
    SanPhamDAO mDAO;
    List<String> mList = new ArrayList<>();
    String loaiSanPham;
    SpinnerAdapter mAdapter;
    SanPhamAdapter mSanPhamAdapter;
    SanPhamDAO mSanPhamDAO;
    boolean flag = false;

    public SnacksFragment() {
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
        View view = inflater.inflate(R.layout.fragment_snacks, container, false);

        //Ánh xạ các thành phần
        mRecyclerView = view.findViewById(R.id.rcv_snacks);
        mFab = view.findViewById(R.id.fb_snacks);
        mDAO = new SanPhamDAO(getActivity());
        mSanPhamDAO = new SanPhamDAO(getActivity());

        capnhatdulieu();

        //Ánh xạ dialog
        mDialog = new Dialog(getActivity());
        mDialog.setContentView(R.layout.add_product_dialog);
        mSpinner = mDialog.findViewById(R.id.spLoaiSanPham);
        edTenSanPham = mDialog.findViewById(R.id.edTenSanPham);
        edSoLuong = mDialog.findViewById(R.id.edSoLuong);
        edDonGia = mDialog.findViewById(R.id.edDonGia);
        btnSave = mDialog.findViewById(R.id.btnSaveDil);
        btnCancel = mDialog.findViewById(R.id.btnCancelDil);

        //sự kiện nút fab
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.show();
            }
        });

        mList.add("Rau củ");
        mList.add("Đồ uống");
        mList.add("Ăn vặt");
        mList.add("Mì gói");
        mList.add("Sữa");
        mList.add("Khác");

        mAdapter = new SpinnerAdapter(getActivity(),mList);
        mSpinner.setAdapter(mAdapter);
        //chọn trong spinner
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loaiSanPham = mList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinner.setSelection(2);
        mSpinner.setEnabled(false);

        //bắt sự kiện nút save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edTenSanPham.getText().toString().equals("")||
                        edDonGia.getText().toString().equals("")||
                        edSoLuong.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Nhập  đầy đủ dữ liệu", Toast.LENGTH_SHORT).show();
                }else{
                    SanPham sanPham = new SanPham();
                    sanPham.setTenSanPham(edTenSanPham.getText().toString());
                    sanPham.setSoLuong(Integer.parseInt(edSoLuong.getText().toString()));
                    sanPham.setDonGia(Double.parseDouble(edDonGia.getText().toString()));
                    sanPham.setLoaiSanPham(loaiSanPham);
                    if(!flag){
                        if(mDAO.insert(sanPham)>0){
                            Toast.makeText(getActivity(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                            edTenSanPham.setText("");
                            edSoLuong.setText("");
                            edDonGia.setText("");
                            capnhatdulieu();
                            mDialog.dismiss();
                        }else{
                            Toast.makeText(getActivity(), "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        //cập nhật dữ liệu sản phẩm
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mDialog.show();
                SanPham sanPham = mSanPhamAdapter.getItem(viewHolder.getAdapterPosition());
                int masp = sanPham.getMaSanPham();
                edDonGia.setText(String.valueOf(sanPham.getDonGia()));
                edTenSanPham.setText(sanPham.getTenSanPham());
                edSoLuong.setText(String.valueOf(sanPham.getSoLuong()));
                flag = true;
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(flag){
                            sanPham.setMaSanPham(masp);
                            sanPham.setSoLuong(Integer.parseInt(edSoLuong.getText().toString()));
                            sanPham.setDonGia(Double.parseDouble(edDonGia.getText().toString()));
                            if(mDAO.update(sanPham) > 0){
                                Toast.makeText(getActivity(), "cập nhật thành công!", Toast.LENGTH_SHORT).show();
                                flag = false;
                                edTenSanPham.setText("");
                                edSoLuong.setText("");
                                edDonGia.setText("");
                                capnhatdulieu();
                                mDialog.dismiss();
                            }else{
                                Toast.makeText(getActivity(), "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
        //bắt sự iện nút cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edTenSanPham.setText("");
                edSoLuong.setText("");
                edDonGia.setText("");
                capnhatdulieu();
            }
        });
        return view;
    }
    private void capnhatdulieu(){
        List<SanPham> list = mSanPhamDAO.getByType("Ăn vặt");
        mSanPhamAdapter = new SanPhamAdapter(getActivity(),list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mSanPhamAdapter);
    }
}