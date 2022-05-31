package com.alexandros.homework5;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.alexandros.homework5.models.User;

import java.util.ArrayList;

// custom adapter for recyclerView
public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>{

    ArrayList<User> userArrayList;
    private final String TAG ="Recycler";

    public UserRecyclerAdapter(){
        this.userArrayList = new ArrayList<>();
        Log.i(TAG, "UserRecyclerAdapter: HERE");
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user,parent,false);
        Log.i(TAG, "onCreateViewHolder: HERE2");
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userArrayList.get(position);
        holder.firstname.setText(user.getFirstname());
        holder.lastName.setText(user.getLastname());
        holder.email.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public void updateUserList(final ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
        notifyDataSetChanged();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextInputEditText firstname;
        private final TextInputEditText lastName;
        private final TextInputEditText email;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            firstname = itemView.findViewById(R.id.userFirstName);
            lastName = itemView.findViewById(R.id.userLastName);
            email = itemView.findViewById(R.id.userEmail);
        }
    }
}
