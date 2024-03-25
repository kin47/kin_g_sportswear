package com.example.kingsportswear.domain.repository;

import com.example.kingsportswear.utils.ResultSetCallback;
import com.example.kingsportswear.utils.models.CustomResult;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public interface AuthRepository {
    void signUp(String email, String password, ResultSetCallback<AuthResult> callback);

    void logIn(String email, String password, ResultSetCallback<FirebaseUser> callback);

    FirebaseUser getCurrentUser();

    CustomResult<Boolean> signOut();
}
