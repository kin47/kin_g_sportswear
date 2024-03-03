package com.example.kingsportswear.data.repository;

import com.example.kingsportswear.data.service.AuthService;
import com.example.kingsportswear.domain.repository.AuthRepository;
import com.example.kingsportswear.utils.models.CustomResult;

import javax.inject.Inject;

public class AuthRepositoryImpl implements AuthRepository {
    private final AuthService authService;

    @Inject
    public AuthRepositoryImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public CustomResult<Boolean> createUserWithEmailAndPassword(String email, String password) {
        try {
            authService.createUserWithEmailAndPassword(email, password);
            return new CustomResult<>(true, null);
        } catch (Exception e) {
            return new CustomResult<>(false, e);
        }
    }

    @Override
    public CustomResult<Boolean> signInWithEmailAndPassword(String email, String password) {
        try {
            authService.signInWithEmailAndPassword(email, password);
            return new CustomResult<>(true, null);
        } catch (Exception e) {
            return new CustomResult<>(false, e);
        }
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
