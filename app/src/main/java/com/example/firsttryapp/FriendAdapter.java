package com.example.firsttryapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder>{

    Context ctx;
    List<User> data;

    public FriendAdapter(Context ctx, List<User> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(ctx).inflate(R.layout.row_friend_layout, parent, false);

        FriendViewHolder holder = new FriendAdapter.FriendViewHolder(root);

        holder.setIsRecyclable(false);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {


        holder.txtName.setText(data.get(position).getUsername().toString());

        holder.row.setOnClickListener(v->{

            NavController navController =
                    Navigation.findNavController((Activity) ctx,R.id.fragmentContainerViewMain);

            Bundle dataBundle = new Bundle();
            dataBundle.putString("operid",data.get(holder.getAdapterPosition()).getUsername());

            navController.navigate(R.id.action_fragmentMain_to_listDataDetailFragment,dataBundle);

        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class FriendViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;

        ConstraintLayout row;


        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtNameFriend);
            row = itemView.findViewById(R.id.rowFriends);


        }
    }
}
