package com.example.fastreding.View.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.fastreding.R;
import com.example.fastreding.db.DatabaseHelper;
import com.example.fastreding.db.Model;
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
    private View view;
    private Model model;
    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_statistic, container, false);

        createChart();
        initBtn();
        return view;
    }

    private void createChart() {
        pieChart = (LineChart) view.findViewById(R.id.chart1);
        LineDataSet lineDataSet = new LineDataSet(new ArrayList<Entry>(), "данные упражнения");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);
        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);
        pieChart.setData(data);
        pieChart.invalidate();
    }

    private void initBtn() {
        btn_1 = (Button) view.findViewById(R.id._1);
        btn_2 = (Button) view.findViewById(R.id._2);
        btn_3 = (Button) view.findViewById(R.id._3);
        btn_4 = (Button) view.findViewById(R.id._4);
        btn_5 = (Button) view.findViewById(R.id._5);
        btn_6 = (Button) view.findViewById(R.id._6);
        btn_7 = (Button) view.findViewById(R.id._7);
        btn_8 = (Button) view.findViewById(R.id._8);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
    }

    private ArrayList<Entry> createDataEntry(ArrayList<Integer> values) {
        ArrayList<Entry> dataValues = new ArrayList<Entry>();
        for (int i = 0; i < values.size(); i++) {
            dataValues.add(new Entry(i, values.get(i)));
        }
        return dataValues;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void changeChart(String nameTable) {
        model = new Model(getActivity().getApplicationContext());
        LineDataSet lineDataSet = new LineDataSet(createDataEntry(model.getPastResult(nameTable)), "данные упражнения");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);
        pieChart.setData(data);
        pieChart.invalidate();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id._1:
                changeChart(DatabaseHelper.TABLE_WORD_PAIR);
                break;

            case R.id._2:
                changeChart(DatabaseHelper.TABLE_SCHULTE);
                break;

            case R.id._3:
                changeChart(DatabaseHelper.TABLE_NUMERICAL);
                break;

            case R.id._4:
                changeChart(DatabaseHelper.TABLE_SEARCH_LETTER);
                break;
            case R.id._5:
                changeChart(DatabaseHelper.TABLE_EVEN_NUMBERS);
                break;

            case R.id._6:
                changeChart(DatabaseHelper.TABLE_DIFFERENT_WORDS);
                break;

            case R.id._7:
                changeChart(DatabaseHelper.TABLE_SUGGESTIONS);
                break;

            case R.id._8:
                changeChart(DatabaseHelper.TABLE_READING_SPEED);
                break;
        }

    }

}
