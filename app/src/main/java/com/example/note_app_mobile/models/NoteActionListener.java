package com.example.note_app_mobile.models;

public interface NoteActionListener {
    void onDeleteNote(Note note, int position);
    void onEditNote(Note note, int position);
    void onViewNote(Note note, int position);
}