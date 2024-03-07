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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    Context ctx;
    List<News> suNew;

    public NewsAdapter(Context ctx, List<News> suNew) {
        this.ctx = ctx;
        this.suNew = suNew;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(ctx).inflate(R.layout.row_news_layout, parent, false);

        NewsAdapter.NewsViewHolder holder = new NewsViewHolder(root);

        holder.setIsRecyclable(false);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {


        holder.txtTitle.setText(suNew.get(position).getTitle().toString());

        holder.row.setOnClickListener(v->{

            NavController navController =
                    Navigation.findNavController((Activity) ctx,R.id.fragmentContainerViewMain);

            Bundle dataBundle = new Bundle();
            dataBundle.putString("operid",suNew.get(holder.getAdapterPosition()).getTitle());

            navController.navigate(R.id.action_fragmentMain_to_fragmentNewsDetail,dataBundle);

        });


    }


    public int getItemCount() {
        return suNew.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitle;

        ConstraintLayout row;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            row = itemView.findViewById(R.id.rowNews);


        }
    }
}
