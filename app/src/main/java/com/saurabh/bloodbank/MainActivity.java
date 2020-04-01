package com.saurabh.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.saurabh.bloodbank.activity.LoginActivity;
import com.saurabh.bloodbank.activity.RegisterActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.registerBtn);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.loginBtn : {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            break;
             }
        case R.id.registerBtn : {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            break;
        }

        }
    }
}
