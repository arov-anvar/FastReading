package com.example.fastreding.View.exercise;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.fastreding.MainContract;
import com.example.fastreding.R;
import com.example.fastreding.View.adapter.DifferentWordsAdapter;
import com.example.fastreding.View.adapter.LetterAdapter;
import com.example.fastreding.View.adapter.TableSchulteAdapter;
import com.example.fastreding.present.PresenterTableSchulte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TableSchulte extends AppCompatActivity implements MainContract.ViewExercise {


    final int MILISECOND_UPDATE = 100;

    private GridView gridNumber;
    private String[] dataNumber;
    private TableSchulteAdapter adapter;
    private PresenterTableSchulte presenter;
    private int widthDisplay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_schulte);

        init();

        gridNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txt = (TextView) view;
                txt.setBackgroundResource(R.color.yellow);
            }
        });

//        final Handler stopWatch = new Handler();
//        stopWatch.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (state) {
//                    Float time = Float.parseFloat(timerTextView.getText().toString()) + 0.1f;
//                    timerTextView.setText(String.format("%.1f", time));
//                    stopWatch.postDelayed(this, MILISECOND_UPDATE);
//                }
//            }
//        }, MILISECOND_UPDATE);
    }


    @Override
    public void init() {
        presenter = new PresenterTableSchulte(getApplicationContext());
        dataNumber = presenter.getArrayNumbers();
        widthDisplay = presenter.getWidth(getWindowManager().getDefaultDisplay());
        gridNumber = (GridView) findViewById(R.id.gridNumbers);
        TableSchulteAdapter adapter = new TableSchulteAdapter(getApplicationContext(), dataNumber, widthDisplay);
        gridNumber.setAdapter(adapter);

    }

    @Override
    public void exerciseEnd() {

    }
}
