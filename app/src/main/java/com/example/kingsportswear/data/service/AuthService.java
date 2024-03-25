package com.example.kingsportswear.data.service;

import com.example.kingsportswear.data.model.Product;
import com.example.kingsportswear.utils.ResultSetCallback;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class AuthService {
    final FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public void createUserWithEmailAndPassword(String email, String password, ResultSetCallback<AuthResult> callback) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
            callback.onSuccess(authResult);
        }).addOnFailureListener(e -> callback.onError(e));
    }

    public void signInWithEmailAndPassword(String email, String password, ResultSetCallback<FirebaseUser> callback) {
        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
            callback.onSuccess(authResult.getUser());
        }).addOnFailureListener(e -> callback.onError(e));
    }

    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    public void signOut() {
        mAuth.signOut();
    }
}
