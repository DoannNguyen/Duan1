package com.example.assigment_duan1.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.assigment_duan1.DAO.HoaDonDAO;
import com.example.assigment_duan1.Model.ThongKe;
import com.example.assigment_duan1.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class DoanhThuFragment extends Fragment {

    ArrayList mKeArrayList;
    List<ThongKe> mList;
    HoaDonDAO mHoaDonDAO;

    public DoanhThuFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);

        mHoaDonDAO = new HoaDonDAO(getActivity());
        mList = new ArrayList<>();
        mList = mHoaDonDAO.getDoanhthu();
        mKeArrayList = new ArrayList();
        BarChart barChart = view.findViewById(R.id.barchart);

        Description description = new Description();
        ArrayList<String> xAxisLabel = new ArrayList<>();
        for (int i = 0 ; i < mList.size() ; i++){
            mKeArrayList.add(new BarEntry(i, (float) mList.get(i).getTienThongKe()));
            xAxisLabel.add(mList.get(i).getTensp());
        }
        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextColor(Color.rgb(0 ,255, 0));
        YAxis yAxis = barChart.getAxisRight();
        yAxis.setEnabled(false);
        barChart.getAxisLeft().setTextColor(Color.rgb(0 ,255, 0));
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        Toast.makeText(getActivity(), ""+xAxisLabel.size(), Toast.LENGTH_SHORT).show();
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        BarDataSet barDataSet = new BarDataSet(mKeArrayList, null);
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setDrawValues(true);
        barDataSet.setValueTextColor(Color.BLACK);

        barChart.getDescription().setEnabled(true);
        return view;
    }

}