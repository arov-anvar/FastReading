package com.example.fastreding.View.exercise;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.fastreding.MainContract;
import com.example.fastreding.R;
import com.example.fastreding.View.ResultExercise;
import com.example.fastreding.View.adapter.TableSchulteAdapter;
import com.example.fastreding.present.PresenterTableSchulte;

import java.util.List;

public class TableSchulte extends AppCompatActivity implements MainContract.ViewExercise {


    final int MILISECOND_UPDATE = 100;
    final String NAME = "Таблица шульта";

    private GridView gridNumber;
    private TextView nextNumber;
    private TextView timerTextView;
    private List<String> dataNumber;
    private TableSchulteAdapter adapter;
    private PresenterTableSchulte presenter;
    private Float time;
    private int widthDisplay;
    private boolean stateStopWatch = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_schulte);
        init();

        gridNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClickItem(view);
            }
        });

        runStopWatch();
    }

    private void runStopWatch() {
        final Handler stopWatch = new Handler();
        stopWatch.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (stateStopWatch) {
                    time = Float.parseFloat(timerTextView.getText().toString()) + 0.1f;
                    timerTextView.setText(String.format("%.1f", time));
                    stopWatch.postDelayed(this, MILISECOND_UPDATE);
                }
            }
        }, MILISECOND_UPDATE);
    }

    private void onClickItem (View view){
        TextView selectedTextView = (TextView) view;
        if (selectedTextView.getText().equals(nextNumber.getText())) {
            nextNumber.setText(incString(nextNumber.getText().toString()));
            selectedTextView.setBackgroundResource(R.color.yellow);
            if (nextNumber.getText().equals("26")) exerciseEnd();
        } else {
            selectedTextView.setBackgroundResource(R.color.red);
        }
        returnBackground(selectedTextView);
    }

    private void returnBackground ( final TextView textView){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setBackground(getResources().getDrawable(R.color.white));
            }
        }, 100);
    }

    private String incString (String str){
        Integer num = Integer.parseInt(str) + 1;
        return num.toString();
    }

    @Override
    public void init () {
        presenter = new PresenterTableSchulte(getApplicationContext());
        dataNumber = presenter.getArrayNumbers();
        widthDisplay = presenter.getWidth(getWindowManager().getDefaultDisplay());
        gridNumber = (GridView) findViewById(R.id.gridNumbers);
        nextNumber = (TextView) findViewById(R.id.nextNumbers);
        timerTextView = (TextView) findViewById(R.id.timer);
        TableSchulteAdapter adapter = new TableSchulteAdapter(getApplicationContext(), dataNumber, widthDisplay);
        gridNumber.setAdapter(adapter);
    }

    @Override
    public void exerciseEnd () {
        stateStopWatch = false;
        Integer countPoint = presenter.getCountPointFromTime(time);
        presenter.setResult(countPoint);

        Intent intent = new Intent(TableSchulte.this, ResultExercise.class);
        intent.putExtra("countPoint", countPoint.toString());
        intent.putExtra("exerciseName", NAME);
        intent.putExtra("countPoint", presenter.getRecord().toString());
        startActivity(intent);
        this.finish();
    }
}
