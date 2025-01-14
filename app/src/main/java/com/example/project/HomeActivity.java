package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void Catatan(View view) {
        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void Kalkulator(View view) {
        Intent intent = new Intent(HomeActivity.this,KalkulatorActivity.class);
        startActivity(intent);
    }
}