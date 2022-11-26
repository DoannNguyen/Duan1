package com.example.assigment_duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment_duan1.Model.NguoiDung;
import com.example.assigment_duan1.R;

import java.util.List;

public class NguoiDungAdapter extends RecyclerView.Adapter<NguoiDungAdapter.NguoiDungViewHolder> {

    private List<NguoiDung> mList;
    private Context mContext;

    public NguoiDungAdapter(List<NguoiDung> list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public NguoiDungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.nguoi_dung_item,null);
        return new NguoiDungViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NguoiDungViewHolder holder, int position) {
        if(mList != null){
            holder.tvTenNguoiDung.setText(mList.get(position).getTenNguoiDung());
            holder.tvSdt.setText(mList.get(position).getSdt());
            if(mList.get(position).getType() == 0){
                holder.tvLoaiNguoiDung.setText("Admin");
            }else{
                holder.tvLoaiNguoiDung.setText("User");
            }
        }
    }

    @Override
    public int getItemCount() {
        if(mList == null){
            return 0;
        }
        return mList.size();
    }

    public static class NguoiDungViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTenNguoiDung, tvSdt, tvLoaiNguoiDung;

        public NguoiDungViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenNguoiDung = itemView.findViewById(R.id.tvTenNguoiDung);
            tvSdt = itemView.findViewById(R.id.tvSdt);
            tvLoaiNguoiDung = itemView.findViewById(R.id.tvLoaiNguoiDung);
        }
    }
}
