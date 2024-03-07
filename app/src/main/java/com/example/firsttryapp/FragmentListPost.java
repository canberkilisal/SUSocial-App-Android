package com.example.firsttryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firsttryapp.databinding.FragmentListPostBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;


public class FragmentListPost extends Fragment {

    FragmentListPostBinding binding;


    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<Post> posts = (List<Post>) msg.obj;

            PostAdapter adp = new PostAdapter(requireContext(), posts);
            binding.recyPost.setAdapter(adp);

            return true;
        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentListPostBinding.inflate(getLayoutInflater());

        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.getSelectedId().observe(getViewLifecycleOwner(), item-> {

            binding.recyPost.setLayoutManager(new LinearLayoutManager(getActivity()));

            binding.addPostButton.setOnClickListener(v -> {

                String usrnm = item.toString();
                Bundle bundle = new Bundle();
                bundle.putString("user", usrnm);

                FragmentManager fragmentManager = getParentFragmentManager();

                fragmentManager.beginTransaction().detach(FragmentListPost.this).commit();

                Navigation.findNavController(v).navigate(R.id.action_fragmentMain_to_fragmentCreatePost, bundle);

            });
        });

        TryRepo repo = new TryRepo();
        ExecutorService srv=((SusoApp)requireActivity().getApplication()).srv;
        repo.listAllPosts(srv,dataHandler);

        return binding.getRoot();
    }
}