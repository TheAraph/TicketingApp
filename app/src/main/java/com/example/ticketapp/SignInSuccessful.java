package com.example.ticketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SignInSuccessful extends AppCompatActivity {

    private Button SignOUT;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_successful);

        SignOUT = findViewById(R.id.SignOUT);

        mAuth = FirebaseAuth.getInstance();
        SignOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInSuccessful.this, MainActivity.class));
            }
        });
    }
    }
