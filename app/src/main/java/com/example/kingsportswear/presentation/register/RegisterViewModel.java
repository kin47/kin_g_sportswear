package com.example.kingsportswear.presentation.register;

import androidx.lifecycle.ViewModel;

import com.example.kingsportswear.domain.repository.AuthRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {
    @Inject
    AuthRepository authRepository;

    @Inject
    public RegisterViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public Task<AuthResult> signUp(String email, String password) {
        return authRepository.signUp(email, password);
    }
}
