package com.alexandros.homework5.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.alexandros.homework5.R;
import com.alexandros.homework5.viewmodel.PasswordResetViewModel;

import java.util.Objects;


public class PasswordResetFragment extends Fragment {

    private PasswordResetViewModel PasswordResetViewModel;
    String email;


    public static boolean isAnyStringNullOrEmpty(String... strings) {
        for (String s : strings)
            if (s == null || s.isEmpty())
                return true;
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            email = getArguments().getString("Email");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password_reset,container,false);
        requireActivity().setTitle(getString(R.string.thk_logo));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputEditText oldEmail = view.findViewById(R.id.passwordResetTxt);
        oldEmail.setText(email);
        PasswordResetViewModel = new ViewModelProvider(this).get(PasswordResetViewModel.class);
        String TAG = "";
        Log.i(TAG, "onViewCreated: here3");
            view.findViewById(R.id.sendPassword).setOnClickListener(view1 -> {
            email = Objects.requireNonNull(oldEmail.getText()).toString().trim();
                if (isAnyStringNullOrEmpty(email)) {
                Toast.makeText(getContext(),"Please enter your email.",Toast.LENGTH_SHORT).show();
            }
            else{
                PasswordResetViewModel.resetPassword(email);
                Navigation.findNavController(view).navigate(R.id.action_passwordResetFragment_to_loginFragment);
            }
        });
    }
}
