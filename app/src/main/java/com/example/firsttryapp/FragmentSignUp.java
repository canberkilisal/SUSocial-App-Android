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

import com.example.firsttryapp.databinding.FragmentLoginBinding;
import com.example.firsttryapp.databinding.FragmentSignUpBinding;

import java.util.Objects;
import java.util.concurrent.ExecutorService;


public class FragmentSignUp extends Fragment {

    FragmentSignUpBinding binding;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            String data = (String) msg.obj;

            if (Objects.equals(data, "User Created!")) {

                Toast.makeText(requireActivity(),"User created successfully!", Toast.LENGTH_SHORT).show();

                NavController navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerViewMain);
                navController.popBackStack(R.id.loginFragment, false);
            }
            else {

                Toast.makeText(requireActivity(),"Username already in use!", Toast.LENGTH_SHORT).show();
            }

            return true;

        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSignUpBinding.inflate(getLayoutInflater());



        binding.submitbtn.setOnClickListener(v->{

            String name = binding.nametext.getText().toString();
            String lastName = binding.surnametext.getText().toString();
            String username = binding.usernametext.getText().toString();
            String password = binding.passtext.getText().toString();


            TryRepo repo = new TryRepo();
            ExecutorService srv = ((SusoApp)getActivity().getApplication()).srv;
            repo.signUpOps(srv,handler,name, lastName, username, password);

        });

        return binding.getRoot();
    }
}