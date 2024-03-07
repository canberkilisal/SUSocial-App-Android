package com.example.firsttryapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.ListDataViewHolder> {

    Context ctx;
    List<User> data;
    private TryRepo repo;
    private ExecutorService srv;
    String username;
    private Activity activity;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            String data = (String) msg.obj;

            if (Objects.equals(data, "Friend added successfully!")) {

                Toast.makeText(activity,"Friend added successfully!", Toast.LENGTH_SHORT).show();

                NavController navController =
                        Navigation.findNavController(activity,R.id.fragmentContainerViewMain);

                navController.popBackStack(R.id.fragmentMain, false);
            }
            else {

                Toast.makeText(activity,"You are already friends!", Toast.LENGTH_SHORT).show();
            }

            return true;
        }
    });

    public UserAdapter(Context ctx, List<User> data, TryRepo repo, ExecutorService srv, String username, Activity activity) {
        this.ctx = ctx;
        this.data = data;
        this.repo = repo;
        this.srv = srv;
        this.username = username;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ListDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(ctx).inflate(R.layout.row_user_layout, parent, false);

        ListDataViewHolder holder = new ListDataViewHolder(root);

        holder.setIsRecyclable(false);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListDataViewHolder holder, int position) {


        holder.txtName.setText(data.get(position).getUsername().toString());

        holder.row.setOnClickListener(v->{

            repo.addFriend(srv, handler, username, data.get(position).getUsername().toString());

        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ListDataViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;

        ConstraintLayout row;


        public ListDataViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            row = itemView.findViewById(R.id.row);


        }
    }

}
