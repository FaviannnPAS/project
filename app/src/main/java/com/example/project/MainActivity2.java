package com.example.project;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText edTitle,edDesc;
    Button button, updateButton;

    NoteHelper noteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        edTitle = findViewById(R.id.edTitle);
        edDesc = findViewById(R.id.edDesc);
        button = findViewById(R.id.addButton);

        updateButton = findViewById(R.id.updateButton);

        noteHelper = new NoteHelper(MainActivity2.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);




//        Update Data
        Intent intent = getIntent();
        final int id = intent.getIntExtra("noteId", -1);  // ID catatan yang ingin diperbarui
        String title = intent.getStringExtra("noteTitle");  // Title catatan
        String description = intent.getStringExtra("noteDesc");  // Deskripsi catatan

        // Isi data ke dalam EditText
        edTitle.setText(title);
        edDesc.setText(description);

        // Ketika tombol "Add Note" ditekan
        button.setOnClickListener(v -> {
            String newTitle = edTitle.getText().toString();
            String newDesc = edDesc.getText().toString();

            if (newTitle.isEmpty() || newDesc.isEmpty()) {
                Toast.makeText(MainActivity2.this, "Pastikan semua kolom diisi", Toast.LENGTH_SHORT).show();
            } else {
                noteHelper.insertData(newTitle, newDesc);  // Menambahkan catatan baru ke database
                Toast.makeText(MainActivity2.this, "Catatan berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                finish();  // Kembali ke MainActivity
            }
        });

        // Ketika tombol "Update Note" ditekan
        updateButton.setOnClickListener(v -> {
            String newTitle = edTitle.getText().toString();
            String newDesc = edDesc.getText().toString();

            if (newTitle.isEmpty() || newDesc.isEmpty()) {
                Toast.makeText(MainActivity2.this, "Pastikan semua kolom diisi", Toast.LENGTH_SHORT).show();
            } else {
                // Update data di database menggunakan id yang sudah ada
                noteHelper.updateData(id, newTitle, newDesc);  // Panggil metode updateData

                // Tampilkan pesan sukses
                Toast.makeText(MainActivity2.this, "Catatan berhasil diperbarui", Toast.LENGTH_SHORT).show();

                // Kembali ke MainActivity setelah update
                finish();  // Menutup MainActivity2 dan kembali ke MainActivity
            }
        });









        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edTitle.length()>0&&edDesc.length()>0){

                    noteHelper.insertData(edTitle.getText().toString(),edDesc.getText().toString());
                    Toast.makeText(MainActivity2.this,"Berhasil Ditambahan",Toast.LENGTH_SHORT).show();
                    edDesc.setText("");
                    edTitle.setText("");
                    startActivity(new Intent(MainActivity2.this,MainActivity.class));


                }else {

                    Toast.makeText(MainActivity2.this,"edit Box Kosong",Toast.LENGTH_SHORT).show();

                }




            }
        });




    }
}