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

import java.util.concurrent.ExecutorService;


public class FragmentUserDetail extends Fragment {

    FragmentListDataDetailBinding binding;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            User data = (User) msg.obj;
            binding.textUsername.setText(data.getName());
            binding.textUsername2.setText(data.getLastName());


            return true;

        }
    });


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListDataDetailBinding.inflate(getLayoutInflater());

        String operid =  getArguments().getString("operid");


        TryRepo repo = new TryRepo();
        ExecutorService srv = ((SusoApp)getActivity().getApplication()).srv;
        repo.getUserByUsername(srv,handler,operid);

        return binding.getRoot();
    }
}