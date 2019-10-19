package com.example.fastreding.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fastreding.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ResultExercise extends AppCompatActivity {

    public TextView textView, textView1;
    private String countPoint, exerciseName, record;
    private ArrayList<Integer> pastResults;
    private TextView yourResult;
    private TextView yourRecord;
    private TextView exerciseNameTextView;
    private LineChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_exercise);

        getIntentData();
        init();
        createChart();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        countPoint = intent.getStringExtra("countPoint");
        exerciseName = intent.getStringExtra("exerciseName");
        record = intent.getStringExtra("record");
        pastResults = intent.getIntegerArrayListExtra("pastResults");
    }

    private void init() {
        yourRecord = (TextView) findViewById(R.id.your_record);
        yourResult = (TextView) findViewById(R.id.your_result);
        exerciseNameTextView = (TextView) findViewById(R.id.exerciseName);
        pieChart = (LineChart) findViewById(R.id.chart);
        if (Float.parseFloat(countPoint) > Float.parseFloat(record)) {
            yourRecord.setText(countPoint);
        } else {
            yourRecord.setText(record);
        }
        yourResult.setText(countPoint);
        exerciseNameTextView.setText(exerciseName);
    }

    private ArrayList<Entry> createDataEntry(ArrayList<Integer> values) {
        ArrayList<Entry> dataValues = new ArrayList<Entry>();
        for (int i = 0; i < values.size(); i++) {
            dataValues.add(new Entry(i, values.get(i)));
        }
        return dataValues;
    }

    private void createChart() {
        LineDataSet lineDataSet = new LineDataSet(createDataEntry(pastResults), "данные упражнения");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);
        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);
        pieChart.setData(data);
        pieChart.invalidate();
    }
}
