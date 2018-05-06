package com.example.shajeeha.mydemoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    //Declaration for Login app
private FirebaseAuth firebaseAuth;
    //declaring UI elements
    private ProgressBar progressLog;
   private  Button Login, reg, end;
   private  EditText username, pass;

    //declaration for animation
    RelativeLayout relay1;

    //For splash screen animation
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            relay1.setVisibility(View.VISIBLE);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //For animation
        relay1 = findViewById(R.id.relay1);

        long delayMillis = 4000;
        handler.postDelayed(runnable, delayMillis);
        //animation stuff ends


        //Login Application
        //Getting values of Ui
        progressLog = findViewById(R.id.progressLogin);
        Login = findViewById(R.id.login_button);
        username = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        reg = findViewById(R.id.register);
        end = findViewById(R.id.exit);



        progressLog.setVisibility(View.GONE);

        firebaseAuth = FirebaseAuth.getInstance();


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        Validate(username.getText().toString(), pass.getText().toString());
            }
        });


        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterationActivity.class));

            }
        });



    }

private void Validate (String username, String password) {

    progressLog.setVisibility(View.VISIBLE);
    firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, LoginSuccessful.class));
            }
            else {
                progressLog.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Incorrect E-mail or Password.", Toast.LENGTH_SHORT).show();
            }
        }
    });

}
}





