package com.example.firsttryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firsttryapp.databinding.FragmentListDataBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;


public class FragmentListUser extends Fragment {

    FragmentListDataBinding binding;


    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<User> data = (List<User>) msg.obj;

            TryRepo repo = new TryRepo();
            ExecutorService srv=((SusoApp)requireActivity().getApplication()).srv;

            Bundle bundle = getArguments();
            String value = bundle.getString("user");

            UserAdapter adp = new UserAdapter(requireContext(), data, repo, srv, value, requireActivity());
            binding.recView.setAdapter(adp);

            return true;
        }
    });


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListDataBinding.inflate(getLayoutInflater());

        binding.recView.setLayoutManager(new LinearLayoutManager(getActivity()));

        TryRepo repo = new TryRepo();
        ExecutorService srv=((SusoApp)requireActivity().getApplication()).srv;
        repo.listAllUsers(srv,dataHandler);

        return binding.getRoot();
    }
}