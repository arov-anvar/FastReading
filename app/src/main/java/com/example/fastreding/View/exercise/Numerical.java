package com.example.fastreding.View.exercise;

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
    private int timeViewWords = 500;
    private Boolean state = false;
    private Integer counterBackground = 0;
    private Integer counter = 0;
    private String rightAnswer;
    private PresenterNumerical presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical);
    }

    public void checkClickNumber(View view) {
//        Button button = (Button) view;
//        Toast.makeText(this, button.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void init() {
        viewNumerical = (EditText) findViewById(R.id.viewNumerical);
        inputBack = (LinearLayout) findViewById(R.id.inputBack);

    }

    @Override
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

    @Override
    public void startExercise() {
        rightAnswer = presenter.createStringNumerical(presenter.getCountNumericalFromLevel());


    }

    @Override
    public void checkAnswer(View view) {

    }

    @Override
    public void exerciseEnd() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putInt("level", level).apply();


    }
}
