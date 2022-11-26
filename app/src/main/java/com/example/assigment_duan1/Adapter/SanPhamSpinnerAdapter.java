package com.example.assigment_duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assigment_duan1.Model.SanPham;
import com.example.assigment_duan1.R;

import java.util.List;

public class SanPhamSpinnerAdapter extends BaseAdapter {

    Context mContext;
    List<SanPham> mList;

    public SanPhamSpinnerAdapter(Context context, List<SanPham> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        if(mList == null){
            return 0;
        }
        return mList.size();
    }

    @Override
    public Object getItem(int position) {

        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.simple_spinner_item,null);
        }
        TextView tv = convertView.findViewById(R.id.tvLoaiSanPham);
        tv.setText(mList.get(position).getTenSanPham());
        return convertView;
    }
}
