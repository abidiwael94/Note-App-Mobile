package com.example.note_app_mobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.note_app_mobile.R;
import com.example.note_app_mobile.models.User;
import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailField, passwordField;
    private Button loginButton;
    private TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = findViewById(R.id.emailInput);
        passwordField = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginBtn);
        registerText = findViewById(R.id.regiterText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();
                if (validateInput(email, password)) {
                    login(email, password);
                }
            }
        });

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToRegister();
            }
        });

    }

    private void redirectToRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validateInput(String email, String password) {
        boolean isValid = true;

        if (email.isEmpty()) {
            emailField.setError("Email is required");
            isValid = false;
        }
        if (password.isEmpty()) {
            passwordField.setError("Password is required");
            isValid = false;
        }

        return isValid;
    }

    private void login(String email, String password) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean userFound = false;

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    if (user != null) {
                        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                            userFound = true;
                            Log.d("FirebaseUser", "User found: " + user.getName());
                            Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                            redirectAfterLogin(user);
                            break;
                        }
                    }
                }

                if (!userFound) {
                    Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FirebaseError", "Error fetching users", databaseError.toException());
                Toast.makeText(LoginActivity.this, "Failed to login. Try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void redirectAfterLogin(User user) {
        if (user.getRole().equals("admin")) {
            Intent intent = new Intent(LoginActivity.this, UserActivity.class);
            startActivity(intent);
        }

        if (user.getRole().equals("user")) {
            Intent intent = new Intent(LoginActivity.this, NoteActivity.class);
            startActivity(intent);
        }
    }
}