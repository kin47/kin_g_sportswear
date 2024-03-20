package com.example.kingsportswear.domain.repository;

import com.example.kingsportswear.utils.models.CustomResult;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface AuthRepository {
    Task<AuthResult> signUp(String email, String password);

    Task<AuthResult> logIn(String email, String password);

    CustomResult<Boolean> checkIfUserIsLoggedIn();

    CustomResult<Boolean> signOut();
}
