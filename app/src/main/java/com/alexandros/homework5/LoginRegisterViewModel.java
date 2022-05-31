package com.alexandros.homework5;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.alexandros.homework5.viewmodel.AuthRepository;

public class LoginRegisterViewModel extends AndroidViewModel {
    private final AuthRepository authRepository;
    private final MutableLiveData<FirebaseUser> userMutableLiveData;

    public LoginRegisterViewModel(@NonNull Application application) {
        super(application);
        this.authRepository = new AuthRepository(application);
        this.userMutableLiveData = authRepository.getUserMutableLiveData();
    }

    public void userRegistration(String firstName, String lastName, String email, String password){
        authRepository.userRegistration(firstName,lastName,email,password);
    }

    public void login(String email, String password) {
        authRepository.login(email,password);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
