package com.example.fastreding.View.exercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.fastreding.MainContract;
import com.example.fastreding.R;
import com.example.fastreding.View.adapter.LetterAdapter;
import com.example.fastreding.present.PresenterSearchLetter;

import org.w3c.dom.Text;

import java.util.Random;

public class SearchLetter extends AppCompatActivity implements MainContract.ViewExercise {

    private final String TEN = "10";

    private GridView gridLetter;
    private LetterAdapter myAdapter;
    private PresenterSearchLetter presenter;
    private TextView textViewRightLetter;
    private TextView textViewCountLeft;
    private Integer countLeft = 10;
    private Integer countPoint = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_letter);
        init();
        createNewDataInAdapter();

        gridLetter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                if (textView.getText().toString().equals(presenter.getRightLetter())) {
                    textView.setBackgroundResource(R.color.yellow);
                    --countLeft;
                    countPoint += 2;
                    textViewCountLeft.setText(countLeft.toString());
                }
                if (countLeft == 0) {
                    textViewCountLeft.setText(TEN);
                    countLeft = 10;
                    createNewDataInAdapter();
                }
            }
        });
    }

    private void createNewDataInAdapter() {
        myAdapter = new LetterAdapter(this, presenter.getArrayLetter());
        gridLetter.setAdapter(myAdapter);
        textViewRightLetter.setText(presenter.getRightLetter());
    }

    public void init() {
        presenter = new PresenterSearchLetter(getApplicationContext());
        textViewRightLetter = (TextView) findViewById(R.id.right_letter);
        textViewCountLeft = (TextView) findViewById(R.id.countLeft);
        gridLetter = (GridView) findViewById(R.id.gridLetter);
    }

    @Override
    public void exerciseEnd() {

    }

}
