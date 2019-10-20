package com.example.fastreding.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fastreding.R;
import com.example.fastreding.present.PresenterProfile;

public class Profile extends AppCompatActivity {

    private TextView bestExercise;
    private TextView worseExercise;
    private TextView middleResult;
    private TextView userName;
    private PresenterProfile presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
    }

    private void init() {
        bestExercise = (TextView) findViewById(R.id.best_exercise);
        worseExercise = (TextView) findViewById(R.id.worse_exercise);
        middleResult = (TextView) findViewById(R.id.middle_result);
        userName = (TextView) findViewById(R.id.user_name);
        presenter = new PresenterProfile(getApplicationContext());
        bestExercise.setText(presenter.getBestExerciseName());
        worseExercise.setText(presenter.getWorseExerciseName());
        middleResult.setText(presenter.getMiddleResult());
        userName.setText(presenter.getUserName());
    }
}
