package com.example.fastreding.View.exercise;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fastreding.MainContract;
import com.example.fastreding.R;
import com.example.fastreding.View.ResultExercise;
import com.example.fastreding.present.PresenterSuggestions;
import com.example.fastreding.present.PresenterWordPair;

public class Suggestions extends AppCompatActivity implements MainContract.ViewExercise {

    final private int BORDER_CHANGE_TIME = 200;
    final private int COUNT_TEST = 10;
    final private int VIEW_COUNT = 5;
    final private String NAME = "Предложения";

    private EditText suggestionWatch;
    private Button checkSuggestionAnswer;
    private Integer countPoint = 0;
    private int timeViewWords = 500;
    private Boolean state = false;
    private Integer counterBackground = 0;
    private Integer counter = 0;
    private String rightAnswer;
    private PresenterSuggestions presenter;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);
        init();
        preparationToStart();
    }

    @Override
    public void init() {
        suggestionWatch = (EditText) findViewById(R.id.suggestionWatch);
        checkSuggestionAnswer = (Button) findViewById(R.id.checkSuggestionAnswer);
        presenter = new PresenterSuggestions(getApplicationContext());
        rightAnswer = presenter.getSuggestion();
    }

    public void preparationToStart() {
        final Handler handler = new Handler();
        suggestionWatch.setClickable(false);
        suggestionWatch.setText("");
        handler.post(new Runnable() {
            @Override
            public void run() {
                ++counterBackground;
                if (state) {
                    state = false;
                    suggestionWatch.setBackground(getResources().getDrawable(R.drawable.border_edit));
                } else {
                    state = true;
                    suggestionWatch.setBackground(getResources().getDrawable(R.color.colorWhite));
                }
                handler.postDelayed(this, BORDER_CHANGE_TIME);
                if (counterBackground == VIEW_COUNT) {
                    handler.removeCallbacks(this);
                    counterBackground = 0;
                    state = false;
                    startExercise();
                }
            }
        });
    }

    public void startExercise() {
        rightAnswer = presenter.getSuggestion();
        suggestionWatch.setText(rightAnswer);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                suggestionWatch.setText("");
                suggestionWatch.setClickable(true);
                checkSuggestionAnswer.setClickable(true);
            }
        }, timeViewWords);
    }

    public void checkSuggestionAnswer(View view) {
        checkSuggestionAnswer.setClickable(false);
        String answer = suggestionWatch.getText().toString().toLowerCase();
        ++counter;
        if (answer.equals(rightAnswer.toLowerCase())) {
            suggestionWatch.setBackground(getResources().getDrawable(R.color.yellow));
            countPoint += presenter.getLevel();
            presenter.changeLevel(true);
        } else {
            suggestionWatch.setBackground(getResources().getDrawable(R.color.red));
            presenter.changeLevel(false);
        }
        if (counter < COUNT_TEST) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    preparationToStart();
                }
            }, 1000);
        } else exerciseEnd();
    }

    @Override
    public void exerciseEnd() {
        //добавление результа упражнения
        presenter.setResult(countPoint);

        // Добавление уровня по оканчанию игры
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putInt("level", countPoint).apply();

        Intent intent = new Intent(Suggestions.this, ResultExercise.class);
        intent.putExtra("countPoint", countPoint.toString());       //передача количесво очков
        intent.putExtra("exerciseName", this.NAME);                 //         название упражнения
        intent.putExtra("record", presenter.getRecord().toString());//         рекорда
        intent.putIntegerArrayListExtra("pastResults", presenter.getPastResult());
        startActivity(intent);
        this.finish();
    }
}
