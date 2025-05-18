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
import com.example.note_app_mobile.models.UserActionListener;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private final List<User> userList;
    private final UserActionListener listener;

    public UserAdapter(List<User> userList, UserActionListener listener) {
        this.userList = userList;
        this.listener = listener;
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
                    listener.onViewUser(user, position);
                    return true;
                } else if (itemId == R.id.action_edit) {
                    listener.onEditUser(user, position);
                    return true;
                } else if (itemId == R.id.action_delete) {
                    listener.onDeleteUser(user, position);
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
            actionButton = itemView.findViewById(R.id.userAction);
        }
    }
}
