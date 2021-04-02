package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;

public class Signup extends AppCompatActivity {
EditText name,email,pass,cnfpass;
Button btn;
TextView textView;
ProgressDialog progressDialog;
private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        cnfpass=findViewById(R.id.cnfpass);
        btn=findViewById(R.id.registerbtn);
        progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Registering User.....");
        mAuth = FirebaseAuth.getInstance();
        textView=findViewById(R.id.log);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this,LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String emails=email.getText().toString().trim();
            String password=pass.getText().toString().trim();
            if (!Patterns.EMAIL_ADDRESS.matcher(emails).matches()){
                email.setError("Invalid Email ");
                email.setFocusable(true);
            } else if ((password.length() < 6)) {

                email.setError("Enter password Grter Then 6 ");
                email.setFocusable(true);


            }else{
                registeruser(emails,password);

            }

            }
        });

    }

    private void registeruser(String emails, String password) {
progressDialog.show();
mAuth.createUserWithEmailAndPassword(emails,password)

                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                progressDialog.dismiss();
                                FirebaseUser user = mAuth.getCurrentUser();
startActivity(new Intent(Signup.this,LoginActivity.class));
 finish();

                            } else {
                                // If sign in fails, display a message to the user.
                               // --->progressDialog.dismiss();

                            }


                        }
                    }).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {

    }
});
        }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}