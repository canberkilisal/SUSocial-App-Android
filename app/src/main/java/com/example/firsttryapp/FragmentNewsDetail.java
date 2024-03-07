package com.example.firsttryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firsttryapp.databinding.FragmentListDataDetailBinding;
import com.example.firsttryapp.databinding.FragmentNewsDetailBinding;

import java.util.concurrent.ExecutorService;


public class FragmentNewsDetail extends Fragment {

    FragmentNewsDetailBinding binding;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            News data = (News) msg.obj;

            binding.txtContent.setText(data.getContent());



            return true;

        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsDetailBinding.inflate(getLayoutInflater());

        String operid =  getArguments().getString("operid");

        TryRepo repo = new TryRepo();
        ExecutorService srv = ((SusoApp)getActivity().getApplication()).srv;
        repo.getNewsByTitle(srv,handler,operid);

        return binding.getRoot();
    }
}