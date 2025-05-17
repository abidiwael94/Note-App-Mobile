package com.example.note_app_mobile.models;

public interface UserActionListener {
    void onDeleteUser(User user, int position);
    void onEditUser(User user, int position);
    void onViewUser(User user, int position);
}
