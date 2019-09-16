package com.example.fastreding.View.exercise;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.fastreding.MainContract;
import com.example.fastreding.R;
import com.example.fastreding.View.ResultExercise;
import com.example.fastreding.View.adapter.DifferentWordsAdapter;
import com.example.fastreding.View.adapter.LetterAdapter;
import com.example.fastreding.present.PresenterDifferentWords;

import java.util.Timer;
import java.util.TimerTask;

public class DifferentWords extends AppCompatActivity implements MainContract.ViewExercise{

    private final String FIVE= "5";
    private final String NAME = "разные слова";
    private GridView gridWords;
    private TextView countLeftWords;
    private PresenterDifferentWords presenter;
    private DifferentWordsAdapter myAdapter;
    private Timer mTimer;
    private Integer countLeft = 5;
    private Integer countPoint = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_words);

        init();
        createNewDataInAdapter();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                exerciseEnd();
            }
        }, 60000);
        gridWords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Button btn = (Button) view;
                TextView txt = (TextView) view;
                String[] arr = txt.getText().toString().split("\n");
                if (!arr[0].equals(arr[1])) {
                    txt.setBackgroundResource(R.color.yellow);
                    --countLeft;
                    countPoint += 2;
                    countLeftWords.setText(countLeft.toString());
                }
                if (countLeft == 0) {
                    countLeftWords.setText(FIVE);
                    countLeft = 10;
                    createNewDataInAdapter();
                }
            }
        });

    }

    @Override
    public void init() {
        gridWords = (GridView) findViewById(R.id.gridWords);
        countLeftWords = (TextView) findViewById(R.id.countLeftWords);
        presenter = new PresenterDifferentWords(getApplicationContext());
        mTimer = new Timer();
    }

    @Override
    public void exerciseEnd() {
        presenter.setResult(countPoint);

        Intent intent = new Intent(DifferentWords.this, ResultExercise.class);
        intent.putExtra("countPoint", countPoint.toString());       //передача количесво очков
        intent.putExtra("exerciseName", this.NAME);                 //         название упражнения
        intent.putExtra("record", presenter.getRecord().toString());//         рекорда
        intent.putIntegerArrayListExtra("pastResults", presenter.getPastResult());
        startActivity(intent);
        this.finish();
    }

    private void createNewDataInAdapter() {
        myAdapter = new DifferentWordsAdapter(this, presenter.getArrayWords());
        gridWords.setAdapter(myAdapter);
    }
}