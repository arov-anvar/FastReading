package com.example.fastreding.View.exercise;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.fastreding.MainContract;
import com.example.fastreding.R;
import com.example.fastreding.View.ResultExercise;
import com.example.fastreding.present.PresenterNumerical;

public class Numerical extends AppCompatActivity implements MainContract.ViewExercise {

    final private int BORDER_CHANGE_TIME = 200;
    final private int COUNT_TEST = 10;
    final private int VIEW_COUNT = 5;
    final private String NAME = "Числа";

    private EditText viewNumerical;
    private LinearLayout inputBack;
    private SharedPreferences preferences;
    private int level;
    private Integer countPoint = 0;
    private int timeViewNumerical = 500;
    private Boolean state = false;
    private Integer counterBackground = 0;
    private Integer counter = 0;
    private String rightAnswer;
    private PresenterNumerical presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical);
        init();
        preparationToStart();
    }


    // инициализация переменных
    @Override
    public void init() {
        viewNumerical = (EditText) findViewById(R.id.viewNumerical);
        inputBack = (LinearLayout) findViewById(R.id.inputBack);
        presenter = new PresenterNumerical(getApplicationContext());

    }

    public void preparationToStart() {
        final Handler handler = new Handler();
        viewNumerical.setText("");
        handler.post(new Runnable() {
            @Override
            public void run() {
                ++counterBackground;
                if (state) {
                    state = false;
                    inputBack.setBackground(getResources().getDrawable(R.drawable.border_edit_blue));
                } else {
                    state = true;
                    inputBack.setBackground(getResources().getDrawable(R.color.blue));
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
        rightAnswer = presenter.createStringNumerical(presenter.getCountNumericalFromLevel());
        viewNumerical.setText(rightAnswer);
        timeViewNumerical = presenter.getTimeFromLevel();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewNumerical.setText(presenter.createUnderLine());
            }
        }, timeViewNumerical);
    }

    public void checkAnswer(View view) {
        Button button = (Button) view;
        String symbol = button.getText().toString();
        String input = viewNumerical.getText().toString();
        int index = input.indexOf("_");
        input = input.substring(0, index) + symbol + input.substring(index + 1);
        viewNumerical.setText(input);
        if (!input.contains("_")) {
            ++counter;
            if (rightAnswer.equals(viewNumerical.getText().toString())) {
                inputBack.setBackground(getResources().getDrawable(R.color.yellow));
                countPoint += presenter.getLevel();
                presenter.changeLevel(true);
            } else {
                inputBack.setBackground(getResources().getDrawable(R.color.red));
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
    }

    public void deleteNumber(View view) {
        String strInput = viewNumerical.getText().toString();
        Integer index = strInput.indexOf("_");
        if (index > 1) {
            strInput = strInput.substring(0, index - 2) + "_" + strInput.substring(index - 1);
        }
        viewNumerical.setText(strInput);
    }

    @Override
    public void exerciseEnd() {

        //добавление результа упражнения
        presenter.setResult(countPoint);

        // Добавление уровня по оканчанию игры
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putInt("level", countPoint).apply();

        Intent intent = new Intent(Numerical.this, ResultExercise.class);
        intent.putExtra("countPoint", countPoint.toString());       //передача количесво очков
        intent.putExtra("exerciseName", this.NAME);                 //         название упражнения
        intent.putExtra("record", presenter.getRecord().toString());//         рекорда
        intent.putIntegerArrayListExtra("pastResults", presenter.getPastResult());
        startActivity(intent);
        this.finish();

    }


}
