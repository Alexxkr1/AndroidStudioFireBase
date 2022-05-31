package com.alexandros.homework5.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.alexandros.homework5.LoginRegisterViewModel;
import com.alexandros.homework5.R;


public class LoginFragment extends Fragment  implements View.OnClickListener{

    NavController navController;
    TextInputEditText username;
    TextInputEditText password;
    private LoginRegisterViewModel loginRegisterViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginRegisterViewModel = new ViewModelProvider(this).get(LoginRegisterViewModel.class);
        loginRegisterViewModel.getUserMutableLiveData().observe(this,firebaseUser -> {
            if (firebaseUser != null) {
                if (getView() != null) Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_userFragment);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.registerBtn).setOnClickListener(this);
        view.findViewById(R.id.loginBtn).setOnClickListener(this);
        view.findViewById(R.id.forgotTxt).setOnClickListener(this);
        username = view.findViewById(R.id.loginUser);
        password = view.findViewById(R.id.loginPass);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("TTHK APP");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerBtn:
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
                break;
            case R.id.forgotTxt:
                navController.navigate(R.id.action_loginFragment_to_PasswordResetFragment);
                break;
            case R.id.loginBtn:
                if (!TextUtils.isEmpty(username.getText()) && !TextUtils.isEmpty(password.getText())) {
                    loginRegisterViewModel.login(username.getText().toString(),password.getText().toString());
                    break;
                } else {
                    Toast.makeText(getActivity(),"missing field input", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}