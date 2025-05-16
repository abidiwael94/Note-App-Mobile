package com.example.note_app_mobile.activities;

import android.os.Bundle;
import android.util.Log;

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

        Log.e("User ", connecteduser.getName());
    }
}
