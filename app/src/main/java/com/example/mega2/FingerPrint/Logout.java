package com.example.mega2.FingerPrint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Logout extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;

    private TextView textViewUserEmail;
    private Button buttonLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, Login.class));
        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewUserEmail = (TextView) findViewById(R.id.textView7);
        buttonLogout = (Button) findViewById(R.id.button2);
        textViewUserEmail.setText("Welcome "+user.getEmail());
        buttonLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Login.class));
        }
    }
}

