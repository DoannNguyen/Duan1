package com.example.assigment_duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assigment_duan1.Model.ItemModel;
import com.example.assigment_duan1.R;

import java.util.List;

public class ItemDapter extends BaseAdapter {
    private Context mContext;
    private List<ItemModel> mList;

    public ItemDapter(Context context, List<ItemModel> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
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
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.product_iem_menu,null);
        }
        ImageView imageView = convertView.findViewById(R.id.image);
        TextView tv = convertView.findViewById(R.id.tvName);

        ItemModel model = mList.get(position);
        imageView.setImageResource(model.getImage());
        tv.setText(model.getName());
        return convertView;
    }
}
