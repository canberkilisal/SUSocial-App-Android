package com.example.firsttryapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

public class CommentAdapter extends  RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{


    Context ctx;
    List<Comment> comments;

    public CommentAdapter(Context ctx, List<Comment> data) {
        this.ctx = ctx;
        this.comments = data;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(ctx).inflate(R.layout.row_comment_layout, parent, false);

        CommentViewHolder holder = new CommentViewHolder(root);

        holder.setIsRecyclable(false);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {


        holder.txtUsername.setText(comments.get(position).getPostOwner().getUsername().toString());
        holder.txtComment.setText(comments.get(position).getContent().toString());
        Log.d("DEV", comments.get(position).getContent().toString());



    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder{

        TextView txtUsername;
        TextView txtComment;

        ConstraintLayout row;


        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUsername = itemView.findViewById(R.id.txtCommentUsrRow);
            txtComment = itemView.findViewById(R.id.txtCommentRow);
            row = itemView.findViewById(R.id.rowComments);


        }
    }

}
