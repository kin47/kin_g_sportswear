package com.example.kingsportswear.data.repository;

import com.example.kingsportswear.data.service.AuthService;
import com.example.kingsportswear.domain.repository.AuthRepository;
import com.example.kingsportswear.utils.models.CustomResult;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AuthRepositoryImpl implements AuthRepository {
    private final AuthService authService;

    @Inject
    public AuthRepositoryImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public Task<AuthResult> signUp(String email, String password) {
           return authService.createUserWithEmailAndPassword(email, password);
    }

    @Override
    public Task<AuthResult> logIn(String email, String password) {
        return authService.signInWithEmailAndPassword(email, password);
    }

    @Override
    public CustomResult<Boolean> checkIfUserIsLoggedIn() {
        try {
            return new CustomResult<>(authService.checkIfUserIsLoggedIn(), null);
        } catch (Exception e) {
            return new CustomResult<>(false, e);
        }
    }

    @Override
    public CustomResult<Boolean> signOut() {
        try {
            authService.signOut();
            return new CustomResult<>(true, null);
        } catch (Exception e) {
            return new CustomResult<>(false, e);
        }
    }
}
