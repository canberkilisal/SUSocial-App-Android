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

public class PostAdapter extends  RecyclerView.Adapter<PostAdapter.PostViewHolder>{

    Context ctx;
    List<Post> posts;

    public PostAdapter(Context ctx, List<Post> posts) {
        this.ctx = ctx;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(ctx).inflate(R.layout.row_post_layout, parent, false);

        PostAdapter.PostViewHolder holder = new PostViewHolder(root);

        holder.setIsRecyclable(false);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {


        holder.txtContent.setText(posts.get(position).getContent().toString());
        holder.txtUsername.setText(posts.get(position).getPostOwner().getUsername().toString());

        holder.row.setOnClickListener(v->{

            NavController navController =
                    Navigation.findNavController((Activity) ctx,R.id.fragmentContainerViewMain);

            Bundle dataBundle = new Bundle();
            dataBundle.putString("operid",posts.get(holder.getAdapterPosition()).getId());

            navController.navigate(R.id.action_fragmentMain_to_fragmentPostDetail,dataBundle);

        });


    }


    public int getItemCount() {
        return posts.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        TextView txtContent;
        TextView txtUsername;
        ConstraintLayout row;


        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            txtContent = itemView.findViewById(R.id.txtPostContent);
            txtUsername = itemView.findViewById(R.id.txtPostUsr);
            row = itemView.findViewById(R.id.rowPosts);


        }
    }

}
