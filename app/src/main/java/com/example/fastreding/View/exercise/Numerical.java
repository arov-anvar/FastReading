package com.example.fastreding.View.exercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fastreding.MainContract;
import com.example.fastreding.R;

public class Numerical extends AppCompatActivity implements MainContract.ViewExercise {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerical);
    }

    public void checkClickNumber(View view) {
        Button button = (Button) view;
        Toast.makeText(this, button.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void init() {

    }

    @Override
    public void preparationToStart() {

    }

    @Override
    public void startExercise() {

    }

    @Override
    public void checkAnswer(View view) {

    }

    @Override
    public void exerciseEnd() {

    }
}
