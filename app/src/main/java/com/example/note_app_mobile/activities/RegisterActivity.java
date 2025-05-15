package com.example.note_app_mobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.note_app_mobile.R;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity  extends AppCompatActivity {

    private TextInputEditText nameInput, emailinput, passwordInput;
    private Button registerbtn;
    private TextView logintext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


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

               Log.d("RegisterActivity", "Name: " + name);
               Log.d("RegisterActivity", "Email: " + email);
               Log.d("RegisterActivity", "Password: " + password);
           }
       });

        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
