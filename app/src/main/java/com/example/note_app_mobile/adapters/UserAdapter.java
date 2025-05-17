package com.example.note_app_mobile.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.note_app_mobile.R;
import com.example.note_app_mobile.models.User;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private final List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);

        holder.userName.setText(user.getName());
        holder.userEmail.setText(user.getEmail());
        holder.userIcon.setImageResource(R.drawable.user_icon);

        holder.actionButton.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.user_item_menu);

            popupMenu.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();

                if (itemId == R.id.action_view) {
                    Log.e("User Action View", user.getName());
                    return true;
                } else if (itemId == R.id.action_edit) {
                    Log.e("User Action Edit", user.getName());
                    return true;
                } else if (itemId == R.id.action_delete) {
                    Log.e("User Action Delete", user.getName());
                    return true;
                }
                return false;
            });

            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userName, userEmail;
        ImageView userIcon;
        ImageButton actionButton;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            userEmail = itemView.findViewById(R.id.userEmail);
            userIcon = itemView.findViewById(R.id.userIcon);
            actionButton = itemView.findViewById(R.id.newUserButton);
        }
    }
}
