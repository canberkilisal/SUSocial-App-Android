package com.example.firsttryapp;

import android.app.Activity;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firsttryapp.databinding.FragmentListDataBinding;
import com.example.firsttryapp.databinding.FragmentListUserFriendBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class FragmentListUserFriend extends Fragment {

    FragmentListUserFriendBinding binding;

    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<User> data = (List<User>) msg.obj;

            FriendAdapter adp = new FriendAdapter(requireContext(), data);
            binding.recyFriend.setAdapter(adp);

            return true;
        }
    });


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListUserFriendBinding.inflate(getLayoutInflater());



        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.getSelectedUsername().observe(getViewLifecycleOwner(), item-> {

            binding.recyFriend.setLayoutManager(new LinearLayoutManager(getActivity()));

            binding.addFriendsButton.setOnClickListener(v -> {


                String usrnm = item.toString();
                Bundle bundle = new Bundle();
                bundle.putString("user", usrnm);

                FragmentManager fragmentManager = getParentFragmentManager();

                fragmentManager.beginTransaction().detach(FragmentListUserFriend.this).commit();

                Navigation.findNavController(v).navigate(R.id.action_fragmentMain_to_fragmentListUser, bundle);

            });

            TryRepo repo = new TryRepo();
            ExecutorService srv=((SusoApp)requireActivity().getApplication()).srv;
            repo.listAllFriends(srv,dataHandler, item);

        });


        return binding.getRoot();
    }

}