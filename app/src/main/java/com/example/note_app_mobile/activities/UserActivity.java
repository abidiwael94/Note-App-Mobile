package com.example.note_app_mobile.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note_app_mobile.R;
import com.example.note_app_mobile.adapters.UserAdapter;
import com.example.note_app_mobile.models.User;
import com.example.note_app_mobile.models.UserActionListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity implements UserActionListener {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        recyclerView = findViewById(R.id.recyclerViewUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        userList = new ArrayList<>();
        userAdapter = new UserAdapter(userList, this);

        recyclerView.setAdapter(userAdapter);

        getAllUsers();
    }

    private void getAllUsers() {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if (user != null) {
                        userList.add(user);
                    }
                }
                userAdapter.notifyDataSetChanged();
                Log.d("UserActivity", "Users count: " + userList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("UserActivity", "Failed to load users", databaseError.toException());
            }
        });
    }

    @Override
    public void onDeleteUser(User user, int position) {
        Log.e("User Action", "delete user" + user.getName());
    }

    @Override
    public void onEditUser(User user, int position) {
        Log.e("User Action", "edit user" + user.getName());
    }

    @Override
    public void onViewUser(User user, int position) {
        Log.e("User Action", "view user " + user.getName());
    }
}
