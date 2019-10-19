package com.example.fastreding.View.exercise;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fastreding.R;
import com.example.fastreding.View.ResultExercise;
import com.example.fastreding.present.PresenterReadingSpeed;

import org.w3c.dom.Text;

public class ReadingSpeed extends AppCompatActivity {

    private final String NAME = "Скорость чтения";

    private PresenterReadingSpeed presenter;
    private TextView textArea;
    private String text;
    private boolean running;
    private int countSeconds = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_speed);
        init();
        startWatch();
    }

    private void init() {
        textArea = (TextView) findViewById(R.id.text_area);
        presenter = new PresenterReadingSpeed(getApplicationContext());
        text = presenter.selectRandomText();
        textArea.setText(text);
    }

    private void startWatch() {
        running = true;
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (running) {
                    countSeconds++;
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }

    public void ready(View view) {
        //
        running = false;
        float speed = (float)presenter.getCountWords(text) / ((float)countSeconds / 60f);
        presenter.setResult((int) speed);
        Intent intent = new Intent(ReadingSpeed.this, ResultExercise.class);
        intent.putExtra("countPoint", Float.toString(speed));       //передача количесво очков
        intent.putExtra("exerciseName", this.NAME);                 //         название упражнения
        intent.putExtra("record", presenter.getRecord().toString());//         рекорда
        intent.putIntegerArrayListExtra("pastResults", presenter.getPastResult());
        startActivity(intent);
        this.finish();
    }
}
