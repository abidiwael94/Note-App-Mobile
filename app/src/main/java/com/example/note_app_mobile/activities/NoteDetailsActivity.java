package com.example.note_app_mobile.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.note_app_mobile.R;
import com.example.note_app_mobile.models.Note;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NoteDetailsActivity extends AppCompatActivity {

    private TextView titleTextView, contentTextView, dateTextView, ownerTextView, onerEmailtextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_note);

        titleTextView = findViewById(R.id.notedetailsTitle);
        contentTextView = findViewById(R.id.notedetailsContent);
        dateTextView = findViewById(R.id.notedetailsDate);
        ownerTextView = findViewById(R.id.notedetailsOwner);
        onerEmailtextView = findViewById(R.id.notedetailsOwnerEmail);

        Note note = (Note) getIntent().getSerializableExtra("note_extra");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);

        if (note != null) {
            titleTextView.setText(note.getTitle());
            contentTextView.setText(note.getContent());
            ownerTextView.setText(note.getOwner().getName());
            onerEmailtextView.setText(note.getOwner().getEmail());
            dateTextView.setText(dateFormat.format(note.getCreatedAt()));
        }
    }
}
