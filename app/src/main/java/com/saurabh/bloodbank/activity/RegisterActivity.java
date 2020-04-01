package com.saurabh.bloodbank.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.saurabh.bloodbank.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button register, login;
    private EditText email, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        auth = FirebaseAuth.getInstance();
        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String email_text = email.getText().toString();
        String password_text = password.getText().toString();
        switch (v.getId()) {
            case R.id.register: {
                if (TextUtils.isEmpty(email_text) && TextUtils.isEmpty(password_text)) {
                    Log.d("register", "Empty Credential");
                    Toast.makeText(this, "Empty Credential", Toast.LENGTH_SHORT).show();
                } else if (password_text.length() < 6) {
                    Log.d("register", "password is too short");
                } else {
                    registerUser(email_text, password_text);
                }
            }
            case R.id.login : {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }
    }

    private void registerUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d("onComplete","user register Successful");
                    Toast.makeText(RegisterActivity.this, "User register Successful", Toast.LENGTH_SHORT).show();
                    login.setVisibility(View.VISIBLE);

                }
                else{
                    Log.d("onComplete","user Not register ");
                    Toast.makeText(RegisterActivity.this, "User not register ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
