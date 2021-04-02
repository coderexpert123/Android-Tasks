package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
EditText log_email,log_pass;
Button log_btn;
TextView textView;

ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        log_email = findViewById(R.id.log_email);
        log_pass = findViewById(R.id.log_pass);
        log_btn = findViewById(R.id.log_btn);
        textView=findViewById(R.id.rg);
        getSupportActionBar().hide();


textView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(LoginActivity.this,Signup.class));
    }
});
        mAuth = FirebaseAuth.getInstance();
        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emails = log_email.getText().toString().trim();
                String password = log_pass.getText().toString().trim();
                if (!Patterns.EMAIL_ADDRESS.matcher(emails).matches()) {
                    log_email.setError("Invalid Email ");
                    log_email.setFocusable(true);
                } else if ((password.length() < 6)) {

                    log_pass.setError("Enter password Grter Then 6 ");
                    log_pass.setFocusable(true);


                } else {
                    loginuser(emails, password);

                }

            }
        });

    }

    private void loginuser(String emails, String password) {


        mAuth.signInWithEmailAndPassword(emails,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this,Dashboard.class));
                            finish();

                        } else {

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    }





