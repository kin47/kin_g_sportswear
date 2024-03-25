package com.example.kingsportswear.presentation.auth;

import androidx.lifecycle.ViewModel;

import com.example.kingsportswear.domain.repository.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {
    @Inject
    AuthRepository authRepository;

    @Inject
    public AuthViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public FirebaseUser getCurrentUser() {
        return authRepository.getCurrentUser();
    }
}
