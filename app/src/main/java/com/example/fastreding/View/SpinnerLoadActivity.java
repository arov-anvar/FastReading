package com.example.fastreding.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fastreding.R;
import com.example.fastreding.View.exercise.DifferentWords;
import com.example.fastreding.View.exercise.EvenNumbers;
import com.example.fastreding.View.exercise.Numerical;
import com.example.fastreding.View.exercise.ReadingSpeed;
import com.example.fastreding.View.exercise.SearchLetter;
import com.example.fastreding.View.exercise.Suggestions;
import com.example.fastreding.View.exercise.TableSchulte;
import com.example.fastreding.View.exercise.WordPair;

import java.util.Timer;
import java.util.TimerTask;

public class SpinnerLoadActivity extends AppCompatActivity {

    private Timer mTimer;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_load);

        Intent intent = getIntent();
        pos = intent.getIntExtra("indexExercise", 0);

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                startExercise(pos);
            }
        }, 3000);
    }

    private void startExercise(int position) {
        switch (position) {
            case 0:
                Intent intent = new Intent(SpinnerLoadActivity.this, WordPair.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(SpinnerLoadActivity.this, TableSchulte.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(SpinnerLoadActivity.this, Numerical.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(SpinnerLoadActivity.this, SearchLetter.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(SpinnerLoadActivity.this, EvenNumbers.class);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(SpinnerLoadActivity.this, DifferentWords.class);
                startActivity(intent);
                break;
            case 6:
                intent = new Intent(SpinnerLoadActivity.this, Suggestions.class);
                startActivity(intent);
                break;
            case 7:
                intent = new Intent(SpinnerLoadActivity.this, ReadingSpeed.class);
                startActivity(intent);
                break;
        }
        finish();
    }
}
