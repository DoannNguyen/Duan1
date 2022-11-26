package com.example.assigment_duan1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.assigment_duan1.Adapter.NguoiDungAdapter;
import com.example.assigment_duan1.DAO.NguoiDungDAO;
import com.example.assigment_duan1.Model.NguoiDung;
import com.example.assigment_duan1.R;

import java.text.ParseException;
import java.util.List;


public class QuanLyNDFragment extends Fragment {

    private List<NguoiDung> mList;
    private RecyclerView mRecyclerView;
    private NguoiDungDAO mDAO;
    private NguoiDungAdapter mAdapter;

    public QuanLyNDFragment() {
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
        View view = inflater.inflate(R.layout.fragment_quan_ly_n_d, container, false);

        mRecyclerView = view.findViewById(R.id.rec_Nguoidung);
        mDAO = new NguoiDungDAO(getActivity());
        try {
            mList = mDAO.getAll();
            Toast.makeText(getActivity(), ""+mList.size(), Toast.LENGTH_SHORT).show();
            mAdapter = new NguoiDungAdapter(mList,getActivity());
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setAdapter(mAdapter);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return view;
    }
}