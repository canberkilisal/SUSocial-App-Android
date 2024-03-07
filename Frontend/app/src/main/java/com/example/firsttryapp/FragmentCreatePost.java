package com.example.firsttryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.firsttryapp.databinding.FragmentCreatePostBinding;
import com.example.firsttryapp.databinding.FragmentListDataDetailBinding;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutorService;


public class FragmentCreatePost extends Fragment {

    FragmentCreatePostBinding binding;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            String data = (String) msg.obj;

            Toast.makeText(requireActivity(),"Post created successfully!", Toast.LENGTH_SHORT).show();

            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerViewMain);
            navController.popBackStack(R.id.fragmentMain, false);

            return true;

        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCreatePostBinding.inflate(getLayoutInflater());

        binding.buttonPost.setOnClickListener(v->{

            String postOut = binding.txtCreatePost.getText().toString();
            String operid =  getArguments().getString("user");
            LocalDateTime postTime = LocalDateTime.now();

            TryRepo repo = new TryRepo();
            ExecutorService srv = ((SusoApp)getActivity().getApplication()).srv;
            repo.createPost(srv,handler,operid, postOut, postTime);

        });

        return binding.getRoot();
    }
}