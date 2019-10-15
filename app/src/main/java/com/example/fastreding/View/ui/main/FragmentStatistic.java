package com.example.fastreding.View.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.fastreding.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FragmentStatistic extends Fragment implements View.OnClickListener {

    private LineChart pieChart;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);

        pieChart = (LineChart) view.findViewById(R.id.chart1);
        LineDataSet lineDataSet1 = new LineDataSet(dataValue1(), "data set 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        LineData data = new LineData(dataSets);
        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);
        pieChart.setData(data);
        pieChart.invalidate();

        return view;
    }

    private ArrayList<Entry> dataValue1() {
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(0, 20));
        dataVals.add(new Entry(1, 33));
        dataVals.add(new Entry(2, 12));
        dataVals.add(new Entry(3, 22));
        return dataVals;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id._1:

                break;

            case R.id._2:

                break;

            case R.id._3:

                break;

            case R.id._4:

                break;
            case R.id._5:

                break;

            case R.id._6:

                break;

            case R.id._7:

                break;

            case R.id._8:

                break;
        }

    }
}
