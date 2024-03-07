package com.example.firsttryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.firsttryapp.databinding.FragmentLoginBinding;
import com.example.firsttryapp.databinding.FragmentMainBinding;

import java.util.Objects;
import java.util.concurrent.ExecutorService;


public class FragmentLogin extends Fragment {

    private SharedViewModel model;
    FragmentLoginBinding binding;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            String data = (String) msg.obj;

            if (Objects.equals(data, "Login Successfully!")) {

                NavController navController =
                        Navigation.findNavController(requireActivity(),R.id.fragmentContainerViewMain);

                navController.navigate(R.id.action_loginFragment_to_fragmentMain);
            }
            else {

                Toast.makeText(requireActivity(),"Invalid username or password", Toast.LENGTH_SHORT).show();
            }

            return true;

        }
    });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(getLayoutInflater());

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        binding.loginbtn.setOnClickListener(v->{

            String username = binding.usernametxt.getText().toString();
            String password = binding.passwordtxt.getText().toString();

            model.setSelectedUsername(username);
            model.setSelectedId("1");

            TryRepo repo = new TryRepo();
            ExecutorService srv = ((SusoApp)getActivity().getApplication()).srv;
            repo.loginOps(srv,handler,username,password);

        });

        binding.signUpbtn.setOnClickListener(v->{


            NavController navController =
                    Navigation.findNavController(requireActivity(),R.id.fragmentContainerViewMain);

            navController.navigate(R.id.action_loginFragment_to_signUpFragment);

        });


        return binding.getRoot();
    }
}