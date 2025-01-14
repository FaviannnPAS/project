package com.example.project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NoteAdepter extends RecyclerView.Adapter<NoteAdepter.ViewHolder> {

    Context context;
    ArrayList<NoteModel> arrayList = new ArrayList<>();

    public NoteAdepter(Context context, ArrayList<NoteModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public NoteAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.note_item,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdepter.ViewHolder holder, int position) {
//      Update
        final NoteModel note = arrayList.get(position);



        holder.tvTitle.setText(arrayList.get(position).getTitle());
        holder.tvDesc.setText(arrayList.get(position).getDescription());



//        Update data
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity2.class);
            intent.putExtra("noteId", note.getId());  // Kirim ID
            intent.putExtra("noteTitle", note.getTitle());  // Kirim Title
            intent.putExtra("noteDesc", note.getDescription());  // Kirim Description
            context.startActivity(intent);  // Mulai Activity2 untuk Update
        });



        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Konfirmasi Hapus")
                    .setMessage("Apakah Anda yakin ingin menghapus catatan ini?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        NoteHelper noteHelper = new NoteHelper(context);
                        noteHelper.deleteData(note.getId());
                        arrayList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, arrayList.size());
                    })
                    .setNegativeButton("Tidak", null)
                    .show();
            return true;
        });




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvDesc;

        ImageButton btnDelete;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);

            btnDelete = itemView.findViewById(R.id.noteTv);



        }
    }
}
