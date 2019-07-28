package com.example.fastreding.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.fastreding.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void training(View view) {
        Intent intent = new Intent(MainActivity.this, Training.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(MainActivity.this, Profile.class);
        startActivity(intent);
    }

    public void information(View view) {
        Intent intent = new Intent(MainActivity.this, Information.class);
        startActivity(intent);
    }
}
