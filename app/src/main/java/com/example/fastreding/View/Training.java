package com.example.fastreding.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.fastreding.R;
import com.example.fastreding.View.exercise.DifferentWords;
import com.example.fastreding.View.exercise.EvenNumbers;
import com.example.fastreding.View.exercise.Numerical;
import com.example.fastreding.View.exercise.ReadingSpeed;
import com.example.fastreding.View.exercise.SearchLetter;
import com.example.fastreding.View.exercise.Suggestions;
import com.example.fastreding.View.exercise.TableSchulte;
import com.example.fastreding.View.exercise.WordPair;
import com.example.fastreding.View.ui.main.SectionsPagerAdapter;

public class Training extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traning);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    public void startExercise(int position) {
        switch (position) {
            case 0:
                Intent intent = new Intent(Training.this, WordPair.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(Training.this, TableSchulte.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(Training.this, Numerical.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(Training.this, SearchLetter.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(Training.this, EvenNumbers.class);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(Training.this, DifferentWords.class);
                startActivity(intent);
                break;
            case 6:
                intent = new Intent(Training.this, Suggestions.class);
                startActivity(intent);
                break;
            case 7:
                intent = new Intent(Training.this, ReadingSpeed.class);
                startActivity(intent);
                break;
        }
    }
}