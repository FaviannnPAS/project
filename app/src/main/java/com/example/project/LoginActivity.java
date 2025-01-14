package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView signupRedirectText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validPassword()){
                } else {
                    checkUser();
                }

            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    public void checkUser() {
        String enteredUsername = loginUsername.getText().toString().trim();
        String enteredPassword = loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        reference.child(enteredUsername).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().exists()) {

                    DataSnapshot dataSnapshot = task.getResult();
                    String databaseUsername = dataSnapshot.child("username").getValue(String.class);
                    String databasePassword = dataSnapshot.child("password").getValue(String.class);

                    if (databaseUsername.equals(enteredUsername) && databasePassword.equals(enteredPassword)) {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("username", enteredUsername);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        loginPassword.setError("Invalid Password");
                    }
                } else {
                    loginUsername.setError("User does not exist");
                }
            } else {
                Toast.makeText(LoginActivity.this, "Error occurred. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Boolean validateUsername () {
        String val = loginUsername.getText().toString();
        if (val.isEmpty()){
            loginUsername.setError("Username cannot be empty");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }

    public Boolean validPassword(){
        String val = loginPassword.getText().toString();
        if (val.isEmpty()){
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }






}