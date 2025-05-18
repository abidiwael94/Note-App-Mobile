package com.example.note_app_mobile.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.note_app_mobile.R;
import com.example.note_app_mobile.models.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = (User) getIntent().getSerializableExtra("user_extra");

        TextView nameTextView, emailTextView, pwdTextView, roleTextView, dateTextView;

        nameTextView = findViewById(R.id.profileName);
        emailTextView = findViewById(R.id.profileEmail);
        pwdTextView = findViewById(R.id.profilePwd);
        roleTextView = findViewById(R.id.profileRole);
        dateTextView = findViewById(R.id.profileDate);


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);

        nameTextView.setText(user.getName());
        emailTextView.setText(user.getEmail());
        pwdTextView.setText(user.getPassword());
        roleTextView.setText(user.getRole());
        dateTextView.setText("- - -");




    }
}
