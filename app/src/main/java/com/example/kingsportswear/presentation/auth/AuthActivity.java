package com.example.kingsportswear.presentation.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;

import com.example.kingsportswear.MainActivity;
import com.example.kingsportswear.MyApp;
import com.example.kingsportswear.R;
import com.example.kingsportswear.databinding.ActivityAuthBinding;

import javax.inject.Inject;

public class AuthActivity extends AppCompatActivity {

    private ActivityAuthBinding binding;

    @Inject
    AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_auth);
        ((MyApp) this.getApplication()).getAppComponent().inject(this);

        if(viewModel.getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("user", viewModel.getCurrentUser());
            startActivity(intent);
        }
    }
}