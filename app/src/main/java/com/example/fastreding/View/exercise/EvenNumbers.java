package com.example.fastreding.View.exercise;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.fastreding.MainContract;
import com.example.fastreding.R;
import com.example.fastreding.View.ResultExercise;
import com.example.fastreding.View.adapter.LetterAdapter;
import com.example.fastreding.present.PresenterEvenNumbers;
import com.example.fastreding.present.PresenterSearchLetter;

import java.util.Timer;
import java.util.TimerTask;

public class EvenNumbers extends AppCompatActivity implements MainContract.ViewExercise {

    private final String TEN = "10";
    private final String NAME = "Четные числа";

    private GridView gridNumbers;
    private TextView countLeftNumbers;
    private PresenterEvenNumbers presenter;
    private Timer mTimer;
    private LetterAdapter myAdapter;
    private Integer countLeft = 10;
    private Integer countPoint = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_even_numbers);

        init();
        createNewDataInAdapter();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                exerciseEnd();
            }
        }, 60000);
        gridNumbers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                if (Integer.parseInt(textView.getText().toString()) % 2 == 0
                        && textView.getBackground() == null) {
                    textView.setBackgroundResource(R.color.yellow);
                    --countLeft;
                    countPoint += 2;
                    countLeftNumbers.setText(countLeft.toString());
                }
                if (countLeft == 0) {
                    countLeftNumbers.setText(TEN);
                    countLeft = 10;
                    createNewDataInAdapter();
                }
            }
        });
    }

    private void createNewDataInAdapter() {
        myAdapter = new LetterAdapter(this, presenter.getArrayNumbers());
        gridNumbers.setAdapter(myAdapter);
    }

    public void init() {
        presenter = new PresenterEvenNumbers(getApplicationContext());
        mTimer = new Timer();
        gridNumbers = (GridView) findViewById(R.id.gridNumbers);
        countLeftNumbers = (TextView) findViewById(R.id.countLeftNumbers);
    }

    @Override
    public void exerciseEnd() {
        //добавление результа упражнения
        presenter.setResult(countPoint);

        Intent intent = new Intent(EvenNumbers.this, ResultExercise.class);
        intent.putExtra("countPoint", countPoint.toString());       //передача количесво очков
        intent.putExtra("exerciseName", this.NAME);                 //         название упражнения
        intent.putExtra("record", presenter.getRecord().toString());//         рекорда
        intent.putIntegerArrayListExtra("pastResults", presenter.getPastResult());
        startActivity(intent);
        this.finish();
    }

}
