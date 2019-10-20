package com.example.fastreding.View;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fastreding.R;
import com.example.fastreding.db.Model;

public class Splash extends AppCompatActivity {

    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        model = new Model(getApplicationContext());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (model.getUserName().equals("null")) {
                    Intent intent = new Intent(Splash.this, Registration.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        }, 3000);
    }
}
