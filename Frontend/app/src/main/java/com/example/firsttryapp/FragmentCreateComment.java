package com.example.firsttryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.firsttryapp.databinding.FragmentCreateCommentBinding;
import com.example.firsttryapp.databinding.FragmentCreatePostBinding;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ExecutorService;


public class FragmentCreateComment extends Fragment {

    FragmentCreateCommentBinding binding;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            String data = (String) msg.obj;

            Toast.makeText(requireActivity(),"Comment created successfully!", Toast.LENGTH_SHORT).show();

            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerViewMain);
            navController.popBackStack(R.id.fragmentMain, false);

            return true;

        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCreateCommentBinding.inflate(getLayoutInflater());

        binding.buttonAddComment.setOnClickListener(v->{

            String commentOut = binding.txtCreateComment.getText().toString();
            String userId =  getArguments().getString("user");
            String operid = getArguments().getString("postId");
            LocalDateTime postTime = LocalDateTime.now();


            TryRepo repo = new TryRepo();
            ExecutorService srv = ((SusoApp)getActivity().getApplication()).srv;
            repo.createComment(srv,handler,userId, commentOut, postTime, operid);

        });

        return binding.getRoot();
    }
}