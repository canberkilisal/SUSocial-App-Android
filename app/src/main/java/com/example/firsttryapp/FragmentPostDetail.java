package com.example.firsttryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firsttryapp.databinding.FragmentPostDetailBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;


public class FragmentPostDetail extends Fragment {

    FragmentPostDetailBinding binding;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            Post data = (Post) msg.obj;

            binding.txtPostDetailUsr.setText(data.getPostOwner().getUsername());
            binding.txtPostDetailContent.setText(data.getContent());

            List<Comment> comments = data.getComments();
            CommentAdapter adp = new CommentAdapter(requireContext(), comments);
            binding.recyPostComments.setAdapter(adp);

            return true;
        }
    });


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPostDetailBinding.inflate(getLayoutInflater());

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());

        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.getSelectedId().observe(getViewLifecycleOwner(), item-> {

            binding.recyPostComments.setLayoutManager(layoutManager);

            binding.buttonAddComment.setOnClickListener(v->{

                String operid =  getArguments().getString("operid");

                String usrnm = item.toString();
                Bundle bundle = new Bundle();
                bundle.putString("user", usrnm);
                bundle.putString("postId", operid);

                FragmentManager fragmentManager = getParentFragmentManager();

                fragmentManager.beginTransaction().detach(FragmentPostDetail.this).commit();

                Navigation.findNavController(v).navigate(R.id.action_fragmentPostDetail_to_fragmentCreateComment, bundle);

            });
        });

        String operid =  getArguments().getString("operid");

        TryRepo repo = new TryRepo();
        ExecutorService srv = ((SusoApp)getActivity().getApplication()).srv;
        repo.getPostById(srv,handler,operid);

        return binding.getRoot();
    }
}