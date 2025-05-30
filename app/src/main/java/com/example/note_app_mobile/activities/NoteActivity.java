package com.example.note_app_mobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note_app_mobile.R;
import com.example.note_app_mobile.adapters.NoteAdapter;
import com.example.note_app_mobile.models.Note;
import com.example.note_app_mobile.models.NoteActionListener;
import com.example.note_app_mobile.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity implements NoteActionListener {

    private User connecteduser;
    private NoteAdapter noteAdapter;
    private List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        connecteduser = (User) getIntent().getSerializableExtra("user_extra");

        RecyclerView recyclerView = findViewById(R.id.recyclerViewNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        noteList = new ArrayList<>();
        noteAdapter = new NoteAdapter(noteList, this);

        recyclerView.setAdapter(noteAdapter);

        newNoteButtonTapped();
        profileButtonTapped();
        getNotes();
}

    private void newNoteButtonTapped() {
        ImageButton newNoteButton = findViewById(R.id.newNoteButton);
        newNoteButton.setOnClickListener(v -> {
            Intent intent = new Intent(NoteActivity.this, CreateNoteActivity.class);
            intent.putExtra("user_extra", connecteduser);
            startActivityForResult(intent, 1);
        });
    }

    private void profileButtonTapped() {
        Button profileButton = findViewById(R.id.userProfileButton);
        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(NoteActivity.this, ProfileActivity.class);
            intent.putExtra("user_extra", connecteduser);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            getNotes();
        }
    }

    private void getNotes() {
        DatabaseReference noteRef = FirebaseDatabase.getInstance().getReference("notes");

        noteRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                noteList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Note note = snapshot.getValue(Note.class);

                    if (connecteduser .getRole().equals("user")) {
                        if (note != null && note.getOwner().getId().equals(connecteduser.getId())) {
                            noteList.add(note);
                        }
                    } else {
                        if (note != null) {
                            noteList.add(note);
                        }
                    }
                }
                noteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("NoteActivity", "Failed to load user's notes", databaseError.toException());
            }
        });
    }

    @Override
    public void onDeleteNote(Note note, int position) {
        DatabaseReference notesRef = FirebaseDatabase.getInstance().getReference("notes");
        notesRef.child(note.getId()).removeValue()
                .addOnSuccessListener(aVoid -> {
                    noteList.remove(position);
                    noteAdapter.notifyItemRemoved(position);
                })
                .addOnFailureListener(e -> {
                    Log.e("NoteActivity", "Failed to delete note", e);
                });
    }

    @Override
    public void onEditNote(Note note, int position) {

    }

    @Override
    public void onViewNote(Note note, int position) {
        Intent intent = new Intent(NoteActivity.this, NoteDetailsActivity.class);
        intent.putExtra("note_extra", note);
        startActivity(intent);
    }
}
