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
import com.saurabh.bloodbank.fragment.HomeActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, OnCompleteListener<AuthResult> {
    private EditText emailText, passwordText;
    private Button login;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = findViewById(R.id.emailLogin);
        passwordText = findViewById(R.id.passwordLogin);
        login = findViewById(R.id.loginBtn);
        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String email_text = emailText.getText().toString();
        String password_text = passwordText.getText().toString();
        switch (v.getId()) {
            case R.id.loginBtn :
                if (TextUtils.isEmpty(email_text) && TextUtils.isEmpty(password_text)) {
                Log.d("login", "Empty Credential");
                Toast.makeText(this, "Empty Credential", Toast.LENGTH_SHORT).show();
            } else if (password_text.length() < 6) {
                Log.d("login", "password is too short");
            } else {
                loginUser(email_text, password_text);
            }
        }

    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this);
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            Log.d("login", "onComplete: Successful intent to Homeactivity");

        }
        else{
            Toast.makeText(this, "Login Failed : "+task.getException(), Toast.LENGTH_SHORT).show();
        }
    }
}
