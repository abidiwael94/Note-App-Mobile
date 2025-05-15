package com.example.note_app_mobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.note_app_mobile.R;
import com.example.note_app_mobile.models.User;
import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;


public class RegisterActivity  extends AppCompatActivity {

    private TextInputEditText nameInput, emailinput, passwordInput;
    private Button registerbtn;
    private TextView logintext;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        usersRef = firebaseDatabase.getReference("users");

        nameInput = findViewById(R.id.inputNameRegister);
        emailinput = findViewById(R.id.inputEmailRegister);
        passwordInput = findViewById(R.id.inputPasswordRegister);
        registerbtn = findViewById(R.id.registerBtn);
        logintext = findViewById(R.id.loginText);

        registerbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String name = nameInput.getText().toString().trim();
               String email = emailinput.getText().toString().trim();
               String password = passwordInput.getText().toString().trim();

               if (validateinput(name, email, password)) {
                   saveAndSaveUser(name, email, password);
               }
           }
       });

        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToLogin();
            }
        });

    }

    private void redirectToLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validateinput(String name, String email, String password) {
        boolean valid = true;

        if (name.isEmpty()) {
            nameInput.setError("Name is required");
            valid = false;
        }

        if (email.isEmpty()) {
            emailinput.setError("Email is required");
            valid = false;
        }

        if (password.isEmpty()) {
            passwordInput.setError("Password is required");
            valid = false;
        }

        return valid;
    }

    private void saveAndSaveUser(String name, String email, String password) {
        String userId = UUID.randomUUID().toString();

        User newUser = new User();
        newUser.setId(userId);
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole("user");

        usersRef.child(userId).setValue(newUser)
                .addOnSuccessListener(aVoid -> {
                    Log.d("RegisterActivity", "User saved successfully");
                    redirectToLogin();
                })
                .addOnFailureListener(e -> {
                    Log.e("RegisterActivity", "Failed to save user", e);
                });

    }
}
