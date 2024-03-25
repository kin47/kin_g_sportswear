package com.example.kingsportswear.data.repository;

import com.example.kingsportswear.data.service.AuthService;
import com.example.kingsportswear.domain.repository.AuthRepository;
import com.example.kingsportswear.utils.ResultSetCallback;
import com.example.kingsportswear.utils.models.CustomResult;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

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
    public void signUp(String email, String password, ResultSetCallback<AuthResult> callback) {
        authService.createUserWithEmailAndPassword(email, password, callback);
    }

    @Override
    public void logIn(String email, String password, ResultSetCallback<FirebaseUser> callback) {
        authService.signInWithEmailAndPassword(email, password, callback);
    }

    @Override
    public FirebaseUser getCurrentUser() {
        return authService.getCurrentUser();
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
