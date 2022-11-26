package com.example.assigment_duan1.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment_duan1.Model.SanPham;
import com.example.assigment_duan1.R;
import com.example.assigment_duan1.fragment.VegetablesFragment;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamViewHolder>{


    private Context mContext;
    private List<SanPham> mList;
    private VegetablesFragment mFragment;

    public SanPhamAdapter(Context context, List<SanPham> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.san_pham_item,null);
       return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
    if(mList != null){
        holder.tvName.setText(mList.get(position).getTenSanPham());
        holder.tvExpiration.setText(String.valueOf(mList.get(position).getDonGia()));
        holder.tvAmount.setText(String.valueOf(mList.get(position).getSoLuong()));
    }

    }

    @Override
    public int getItemCount() {
        if (mList == null){
            return 0;
        }
        return mList.size();
    }

    public SanPham getItem(int position){
        if(mList == null || position >= mList.size()){
            return null;
        }
        return mList.get(position);
    }

    public static class SanPhamViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvExpiration, tvAmount;
        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameProduct);
            tvExpiration = itemView.findViewById(R.id.tvExpiration);
            tvAmount = itemView.findViewById(R.id.tvAmount);
        }
    }

}
