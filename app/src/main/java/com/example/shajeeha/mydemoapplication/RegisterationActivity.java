package com.example.shajeeha.mydemoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterationActivity extends AppCompatActivity {


    private Button Register, SignIn;
    private EditText username, pass, Email;
    private ProgressBar pbar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        //assigning values to varaibles
        Register = findViewById(R.id.Reg_button);
        username = findViewById(R.id.userName);
        pass = findViewById(R.id.userpassword);
        Email = findViewById(R.id.userEmail);
        pbar = findViewById(R.id.progressReg);
        SignIn = findViewById(R.id.SignIn);

        pbar.setVisibility(View.GONE);
        firebaseAuth = FirebaseAuth.getInstance();

        //When Sign in is pressed
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterationActivity.this, MainActivity.class));

            }
        });

//When Register is pressed
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbar.setVisibility(View.VISIBLE);
               if(Validate())
               {

                   String UserEmail = Email.getText().toString().trim();
                   String UserPassword = pass.getText().toString().trim();
                   String UserName = username.getText().toString().trim();

                   firebaseAuth.createUserWithEmailAndPassword(UserEmail, UserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful())
                           {Toast.makeText(getApplicationContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();}
                           else {Toast.makeText(getApplicationContext(), "Registration Failed. Please Try Again.", Toast.LENGTH_SHORT).show();}
                           pbar.setVisibility(View.GONE);

                       }
                   });

               }

            }
        });




    }

    private Boolean Validate () {
        Boolean result = false;
        String name = username.getText().toString();
        String email = Email.getText().toString();
        String passw = pass.getText().toString();

        if (name.isEmpty() || passw.isEmpty() || email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter all the details.", Toast.LENGTH_SHORT).show();
            pbar.setVisibility(View.GONE);
        }
        else {result = true;}
        return result;
    }
}
