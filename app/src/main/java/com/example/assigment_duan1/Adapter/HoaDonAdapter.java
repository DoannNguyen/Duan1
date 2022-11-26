package com.example.assigment_duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment_duan1.Model.HoaDon;
import com.example.assigment_duan1.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonVewHolder>{

    List<HoaDon> mList;
    Context mContext;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public HoaDonAdapter(List<HoaDon> list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public HoaDonVewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hoa_don_item,null);
        return new HoaDonVewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonVewHolder holder, int position) {
        if(holder != null){
            holder.tvTenSanPham.setText(mList.get(position).getTenSanPham());
            holder.tvSoLuong.setText(String.valueOf(mList.get(position).getDonGia()));
            holder.tvTenNguoiTao.setText(mList.get(position).getTenNguoiTao());
            holder.tvNgayTao.setText(sdf.format(mList.get(position).getNgay()));
        }
    }

    @Override
    public int getItemCount() {
        if(mList == null){
            return 0;
        }
        return mList.size();
    }

    public static class HoaDonVewHolder extends RecyclerView.ViewHolder {
        TextView tvTenSanPham, tvSoLuong, tvTenNguoiTao, tvNgayTao;

        public HoaDonVewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSanPham = itemView.findViewById(R.id.tvTenSanPham);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            tvTenNguoiTao = itemView.findViewById(R.id.tvTenNguoiTao);
            tvNgayTao = itemView.findViewById(R.id.tvNgayTao);
        }
    }
}
