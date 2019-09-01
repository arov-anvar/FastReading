package com.example.fastreding.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fastreding.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ResultExercise extends AppCompatActivity {

    public TextView textView, textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_exercise);
        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView1);
        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("countPoint"));
        textView1.setText(intent.getStringExtra("exerciseName"));

        String a1 = "";// = intent.getStringExtra("record");
        ArrayList<Integer> a2 = intent.getIntegerArrayListExtra("pastResult");
        if (a2 != null) {
            for (int i = 0; i < a2.size(); i++) {
                a1 += a2.get(i);
            }
        }
        textView1.setText(a1);
    }
}
