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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.alexandros.homework5.LoginRegisterViewModel;
import com.alexandros.homework5.R;
import com.alexandros.homework5.viewmodel.UserViewModel;

public class RegisterFragment extends Fragment {

    NavController navController;
    TextInputEditText firstName;
    TextInputEditText lastName;
    TextInputEditText email;
    TextInputEditText password;
    TextInputEditText passwordConfirm;
    private LoginRegisterViewModel loginRegisterViewModel;
    private UserViewModel userViewModel;

    private static final String TAG = "MyActivity";
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        loginRegisterViewModel = new ViewModelProvider(this).get(LoginRegisterViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUserMutableLiveData().observe(this,firebaseUser -> {
            if (firebaseUser != null) {
                Toast.makeText(getContext(),"User logged in", Toast.LENGTH_SHORT).show();
                // TODO: 1 user is logged in -> shouldn't be able to come here
            }
        });

        userViewModel.getLoggedOutMutableLiveData().observe(this,loggedOut -> {
            if (loggedOut){
                if (getView() != null){
                    Log.i(TAG, "onCreate: "+ getView());
                    Navigation.findNavController(getView()).navigate(R.id.action_registerFragment_to_loginFragment2);
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_registration, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        firstName = view.findViewById(R.id.firstNameTxt);
        lastName = view.findViewById(R.id.lastnameTxt);
        email = view.findViewById(R.id.emailTxt);
        password = view.findViewById(R.id.passwordTxt);
        passwordConfirm = view.findViewById(R.id.passwordConfirmTxt);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("User registration");

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.register){
            if (TextUtils.isEmpty(firstName.getText()) || TextUtils.isEmpty(lastName.getText()) || TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(password.getText()) || TextUtils.isEmpty(passwordConfirm.getText())) {
                Toast.makeText(getActivity(),"missing field input", Toast.LENGTH_SHORT).show();
            }
            else if (password.getText().toString().length() < 6 || passwordConfirm.getText().toString().length() < 6 ){
                Toast.makeText(getActivity(),"Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            }
             else if (!password.getText().toString().equals(passwordConfirm.getText().toString())) {
                Toast.makeText(getActivity(),"Passwords don't match", Toast.LENGTH_SHORT).show();
            }
            else if (!isEmailValid(email.getText().toString())){
                Toast.makeText(getActivity(),"Email is invalid", Toast.LENGTH_SHORT).show();
            }
            else {
                loginRegisterViewModel.userRegistration(firstName.getText().toString(),lastName.getText().toString(),email.getText().toString(),password.getText().toString());
                userViewModel.logOut();


            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_register,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}