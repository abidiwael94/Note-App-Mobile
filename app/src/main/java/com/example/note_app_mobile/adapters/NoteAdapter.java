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
import com.example.note_app_mobile.models.Note;
import com.example.note_app_mobile.models.NoteActionListener;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NioteViewHolder> {

    private final List<Note> noteList;
    private final NoteActionListener listener;

    public NoteAdapter(List<Note> noteList, NoteActionListener listener) {
        this.noteList = noteList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NioteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_note, parent, false);
        return new NioteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NioteViewHolder holder, int position) {
        Note note = noteList.get(position);

        holder.noteTitle.setText(note.getTitle());
        holder.noteContent.setText(note.getContent());
        holder.noteIcon.setImageResource(R.drawable.note_icon);

        holder.actionButton.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.note_item_menu);

            popupMenu.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();

                if (itemId == R.id.action_view) {
                    listener.onViewNote(note, position);
                    return true;
                } else if (itemId == R.id.action_edit) {
                    listener.onEditNote(note, position);
                    return true;
                } else if (itemId == R.id.action_delete) {
                    listener.onDeleteNote(note, position);
                    return true;
                }
                return false;
            });

            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    static class NioteViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle, noteContent;
        ImageView noteIcon;
        ImageButton actionButton;

        NioteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.noteTitle);
            noteContent = itemView.findViewById(R.id.noteContent);
            noteIcon = itemView.findViewById(R.id.noteIcon);
            actionButton = itemView.findViewById(R.id.noteAction);
        }
    }
}
