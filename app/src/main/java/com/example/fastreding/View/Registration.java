package com.example.fastreding.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.fastreding.R;
import com.example.fastreding.db.Model;

public class Registration extends AppCompatActivity {

    private Model model;
    private EditText inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        model = new Model(getApplicationContext());
        inputName = (EditText) findViewById(R.id.input_user_name);
    }

    public void addUser(View view) {
        model.setUserName(inputName.getText().toString());
        Intent intent = new Intent(Registration.this, MainActivity.class);
        startActivity(intent);
    }
}
