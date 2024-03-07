package com.example.firsttryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firsttryapp.databinding.FragmentListNewsBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;


public class FragmentListNews extends Fragment {

    FragmentListNewsBinding binding;

    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<News> suNew = (List<News>) msg.obj;

            NewsAdapter adp = new NewsAdapter(requireContext(), suNew);
            binding.recyNews.setAdapter(adp);

            return true;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListNewsBinding.inflate(getLayoutInflater());

        binding.recyNews.setLayoutManager(new LinearLayoutManager(getActivity()));


        TryRepo repo = new TryRepo();

        ExecutorService srv=((SusoApp)requireActivity().getApplication()).srv;

        repo.listAllNews(srv,dataHandler);

        return binding.getRoot();
    }
}