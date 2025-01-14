package com.example.project;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingId;
    RecyclerView recyclerView;
    ArrayList<NoteModel> arrayList = new ArrayList<>();
    NoteHelper noteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        floatingId = findViewById(R.id.floatingId);
        recyclerView = findViewById(R.id.recyclerView);
        noteHelper = new NoteHelper(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Cursor cursor = noteHelper.showData();
        while (cursor.moveToNext()){

            arrayList.add(new NoteModel(cursor.getString(1), cursor.getString(2),cursor.getInt(0)));


        }

        NoteAdepter adepter = new NoteAdepter(this,arrayList);
        recyclerView.setAdapter(adepter);


        floatingId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,MainActivity2.class));


            }
        });










    }


//  Update Data
    @Override
    protected void onResume() {
        super.onResume();

        // Ambil data terbaru setelah update
        Cursor cursor = noteHelper.showData();  // Ambil data terbaru dari database
        arrayList.clear();  // Clear data lama
        while (cursor.moveToNext()) {
            arrayList.add(new NoteModel(cursor.getString(1), cursor.getString(2), cursor.getInt(0)));
        }

        // Notifikasi adapter agar RecyclerView diperbarui
        NoteAdepter adapter = new NoteAdepter(this, arrayList);  // Membuat adapter baru
        recyclerView.setAdapter(adapter);  // Set adapter untuk RecyclerView
        adapter.notifyDataSetChanged();  // Perbarui RecyclerView
    }






}