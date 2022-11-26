package com.example.assigment_duan1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.assigment_duan1.Adapter.ItemDapter;
import com.example.assigment_duan1.Model.ItemModel;
import com.example.assigment_duan1.R;

import java.util.ArrayList;
import java.util.List;

public class SanPhamFragment extends Fragment {

    GridView grip_product;
    List<ItemModel> mList;
    ItemDapter mDapter;

    public SanPhamFragment() {
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
        View view = inflater.inflate(R.layout.fragment_san_pham, container, false);

        //tạo các tùy chọn cho loại hàng
        grip_product = view.findViewById(R.id.grip_products);
        mList = new ArrayList<>();
        mList.add(new ItemModel(R.drawable.vegetables,"Rau củ"));
        mList.add(new ItemModel(R.drawable.drink,"Đồ uống"));
        mList.add(new ItemModel(R.drawable.snack,"Ăn vặt"));
        mList.add(new ItemModel(R.drawable.noodles,"Mì gói"));
        mList.add(new ItemModel(R.drawable.milk,"Sữa"));
        mList.add(new ItemModel(R.drawable.basket,"Khác"));
        mDapter = new ItemDapter(getActivity(),mList);
        grip_product.setAdapter(mDapter);

        //tạo sự kiện onclick cho các loại hàng, click loại hàng nào sẽ chuyển qua danh sách loại hàng đó
        grip_product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                switch (position){
                    case 0:
                        VegetablesFragment vegetablesFragment = new VegetablesFragment();

                        ft.replace(getId(),vegetablesFragment);
                        //ft.add(getId(),new VegetablesFragment());
                        ft.addToBackStack(vegetablesFragment.getClass().getSimpleName());
                        ft.commit();
                        break;
                    case 1:
                        DrinksFragment drinksFragment = new DrinksFragment();

                        ft.replace(getId(),drinksFragment);
                        ft.addToBackStack(drinksFragment.getClass().getSimpleName());
                        ft.commit();
                        break;
                    case 2:
                        SnacksFragment snacksFragment = new SnacksFragment();
                        ft.replace(getId(),snacksFragment);
                        ft.addToBackStack(snacksFragment.getClass().getSimpleName());
                        ft.commit();
                        break;
                    case 3:
                        NoodlesFragment noodlesFragment = new NoodlesFragment();
                        ft.replace(getId(),noodlesFragment);
                        ft.addToBackStack(noodlesFragment.getClass().getSimpleName());
                        ft.commit();
                        break;
                    case 4:
                        MilksFragment milksFragment = new MilksFragment();
                        ft.replace(getId(),milksFragment);
                        ft.addToBackStack(milksFragment.getClass().getSimpleName());
                        ft.commit();
                        break;
                    case 5:
                        BasketFragment basketFragment = new BasketFragment();
                        ft.replace(getId(),basketFragment);
                        ft.addToBackStack(basketFragment.getClass().getSimpleName());
                        ft.commit();
                        break;
                }
            }
        });
        return view;
    }

}