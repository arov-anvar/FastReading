package com.example.fastreding.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fastreding.R;

public class ResultExercise extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_exercise);
        textView = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("countPoint"));
    }
}
