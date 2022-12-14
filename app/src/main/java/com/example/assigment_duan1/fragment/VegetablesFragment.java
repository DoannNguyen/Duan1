package com.example.assigment_duan1.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
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


public class VegetablesFragment extends Fragment{

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

    public VegetablesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_vegetables, container, false);

        //??nh x??? c??c th??nh ph???n
        mRecyclerView = view.findViewById(R.id.rcv_vegetable);
        mFab = view.findViewById(R.id.fb_vegetable);
        mDAO = new SanPhamDAO(getActivity());
        mSanPhamDAO = new SanPhamDAO(getActivity());

        capnhatdulieu();

        //??nh x??? dialog
        mDialog = new Dialog(getActivity());
        mDialog.setContentView(R.layout.add_product_dialog);
        mSpinner = mDialog.findViewById(R.id.spLoaiSanPham);
        edTenSanPham = mDialog.findViewById(R.id.edTenSanPham);
        edSoLuong = mDialog.findViewById(R.id.edSoLuong);
        edDonGia = mDialog.findViewById(R.id.edDonGia);
        btnSave = mDialog.findViewById(R.id.btnSaveDil);
        btnCancel = mDialog.findViewById(R.id.btnCancelDil);

        //s??? ki???n n??t fab
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.show();
            }
        });

        mList.add("Rau c???");
        mList.add("????? u???ng");
        mList.add("??n v???t");
        mList.add("M?? g??i");
        mList.add("S???a");
        mList.add("Kh??c");

        mAdapter = new SpinnerAdapter(getActivity(),mList);
        mSpinner.setAdapter(mAdapter);
        //ch???n trong spinner
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loaiSanPham = mList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinner.setSelection(0);
        mSpinner.setEnabled(false);

        //b???t s??? ki???n n??t save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edTenSanPham.getText().toString().equals("")||
                edDonGia.getText().toString().equals("")||
                edSoLuong.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Nh???p  ?????y ????? d??? li???u", Toast.LENGTH_SHORT).show();
                }else{
                    SanPham sanPham = new SanPham();
                    sanPham.setTenSanPham(edTenSanPham.getText().toString());
                    sanPham.setSoLuong(Integer.parseInt(edSoLuong.getText().toString()));
                    sanPham.setDonGia(Double.parseDouble(edDonGia.getText().toString()));
                    sanPham.setLoaiSanPham(loaiSanPham);
                    if(!flag){
                        if(mDAO.insert(sanPham)>0){
                            Toast.makeText(getActivity(), "Th??m th??nh c??ng!", Toast.LENGTH_SHORT).show();
                            edTenSanPham.setText("");
                            edSoLuong.setText("");
                            edDonGia.setText("");
                            capnhatdulieu();
                            mDialog.dismiss();
                        }else{
                            Toast.makeText(getActivity(), "Th??m th???t b???i!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        //s??t ph????ng th???c x??a
//        ItemTouchHelper helper =new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT ) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                int position =viewHolder.getAdapterPosition();
//                SanPham sanPham = mSanPhamAdapter.getItem(position);
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setTitle("X??a s???n ph???m").setMessage("B???n c?? mu???n x??a s???n ph???m n??y ?")
//                        .setNegativeButton("yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                mSanPhamDAO.delete(String.valueOf(sanPham.getMaSanPham()));
//                            }
//                        }).setPositiveButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                capnhatdulieu();
//                            }
//                        });
//                builder.create().show();
//            }
//        });
//        helper.attachToRecyclerView(mRecyclerView);

        //c???p nh???t d??? li???u s???n ph???m
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
                                Toast.makeText(getActivity(), "c???p nh???t th??nh c??ng!", Toast.LENGTH_SHORT).show();
                                flag = false;
                                edTenSanPham.setText("");
                                edSoLuong.setText("");
                                edDonGia.setText("");
                                capnhatdulieu();
                                mDialog.dismiss();
                            }else{
                                Toast.makeText(getActivity(), "C???p nh???t th???t b???i!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
        helper.attachToRecyclerView(mRecyclerView);

        //b???t s??? i???n n??t cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edTenSanPham.setText("");
                edSoLuong.setText("");
                edDonGia.setText("");
                mDialog.cancel();
                capnhatdulieu();
            }
        });
        return view;
    }
    private void capnhatdulieu(){
        List<SanPham> list = mSanPhamDAO.getByType("Rau c???");
        mSanPhamAdapter = new SanPhamAdapter(getActivity(),list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mSanPhamAdapter);
    }

}