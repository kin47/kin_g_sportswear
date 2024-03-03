package com.example.kingsportswear.domain.repository;

import com.example.kingsportswear.utils.models.CustomResult;

public interface AuthRepository {
    CustomResult<Boolean> createUserWithEmailAndPassword(String email, String password);
    CustomResult<Boolean> signInWithEmailAndPassword(String email, String password);
    CustomResult<Boolean> checkIfUserIsLoggedIn();
    CustomResult<Boolean> signOut();
}
