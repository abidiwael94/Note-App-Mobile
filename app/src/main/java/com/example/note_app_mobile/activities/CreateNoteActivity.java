package com.example.note_app_mobile.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.note_app_mobile.R;
import com.example.note_app_mobile.models.Note;
import com.example.note_app_mobile.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.UUID;

public class CreateNoteActivity extends AppCompatActivity {

    private EditText titleInput, contentInput;
    private Button saveButton;
    private User owner;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference notesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        notesRef = firebaseDatabase.getReference("notes");

        titleInput = findViewById(R.id.titleInput);
        contentInput = findViewById(R.id.contentInput);
        saveButton = findViewById(R.id.saveButton);

        owner = (User) getIntent().getSerializableExtra("user_extra");

        saveButton.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String content  = contentInput.getText().toString();

            if(validateInput(title, content)) {
                createNote(title, content, owner);
            }
        });
    }

    private boolean validateInput(String title, String content) {
        boolean valid = true;

        if (title.isEmpty()) {
            titleInput.setError("title is required");
            valid = false;
        }

        if (content.isEmpty()) {
            contentInput.setError("content is required");
            valid = false;
        }
        return valid;
    }

    private void createNote(String title, String content, User owner) {
        String noteId = UUID.randomUUID().toString();

        Note newNote = new Note(noteId, title, content, new Date(), new Date(), owner);

        notesRef.child(noteId).setValue(newNote)
                .addOnSuccessListener(aVoid -> {
                    Log.d("CreateNoteActivity", "Note saved successfully");
                    finish(); // Optional: Close the activity after saving
                })
                .addOnFailureListener(e -> {
                    Log.e("CreateNoteActivity", "Failed to save note", e);
                });
    }
}
