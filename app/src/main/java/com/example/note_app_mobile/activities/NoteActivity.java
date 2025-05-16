package com.example.note_app_mobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import com.example.note_app_mobile.R;
import com.example.note_app_mobile.models.User;

public class NoteActivity extends AppCompatActivity {

    private User connecteduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        connecteduser = (User) getIntent().getSerializableExtra("user_extra");

        ImageButton newNoteButton = findViewById(R.id.newNoteButton);
        newNoteButton.setOnClickListener(v -> {
            Intent intent = new Intent(NoteActivity.this, CreateNoteActivity.class);
            intent.putExtra("user_extra", connecteduser);
            startActivity(intent);
        });
    }
}
