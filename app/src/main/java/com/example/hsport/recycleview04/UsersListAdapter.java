package com.example.hsport.recycleview04;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {
    public List<Users> usersList;
    LayoutInflater mInflater;
    public UsersListAdapter(List<Users>usersList){
        this.usersList = usersList;

    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public TextView nameText;
        public TextView statusText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            nameText = mView.findViewById(R.id.tvName);
            statusText = mView.findViewById(R.id.tvStatus);
        }
    }

    @NonNull
    @Override
    public UsersListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersListAdapter.ViewHolder holder, int position) {
        holder.nameText.setText(usersList.get(position).getName());
        holder.statusText.setText(usersList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
