package com.example.kingsportswear.data.service;

import com.google.firebase.auth.FirebaseAuth;

public class AuthService {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public void createUserWithEmailAndPassword(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password);
    }

    public void signInWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password);
    }

    public boolean checkIfUserIsLoggedIn() {
        return mAuth.getCurrentUser() != null;
    }

    public void signOut() {
        mAuth.signOut();
    }
}
