package com.example.ticketapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText EMAIL, PASSWORD;
    private TextView NoAccount;
    private Button SIGNINBTN;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EMAIL = findViewById(R.id.EMAIL);
        PASSWORD = findViewById(R.id.PASSWORD);
        SIGNINBTN = findViewById(R.id.SIGNINBTN);
        NoAccount = findViewById(R.id.NoAccount);

        mAuth = FirebaseAuth.getInstance();
        NoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUP.class));
            }
        });
        SIGNINBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }
        private void loginUser(){
            String email = EMAIL.getText().toString();
            String pass = PASSWORD.getText().toString();

            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if (!pass.isEmpty()){
                    mAuth.signInWithEmailAndPassword(email , pass)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(MainActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity.this , SignInSuccessful.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    PASSWORD.setError("Empty Fields Are not Allowed");
                }
            }else if(email.isEmpty()){
                EMAIL.setError("Empty Fields Are not Allowed");
            }else{
                EMAIL.setError("Pleas Enter Correct Email");
            }
        }

    }
