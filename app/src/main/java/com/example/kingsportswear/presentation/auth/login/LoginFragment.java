package com.example.kingsportswear.presentation.auth.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.kingsportswear.MainActivity;
import com.example.kingsportswear.MyApp;
import com.example.kingsportswear.R;
import com.example.kingsportswear.databinding.FragmentLoginBinding;
import com.example.kingsportswear.utils.LoadingUtils;

import javax.inject.Inject;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    @Inject
    LoginViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((MyApp) getActivity().getApplication()).getAppComponent().inject(LoginFragment.this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvLoginRedirectToSignup.setOnClickListener(view1 -> NavHostFragment.findNavController(this)
                .navigate(R.id.action_LoginFragment_to_RegisterFragment));

        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etLoginInputEmail.getText().toString();
            String password = binding.etLoginInputPassword.getText().toString();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), getString(R.string.please_fill_all_field), Toast.LENGTH_SHORT).show();
            } else {
                viewModel.logIn(email, password);
            }
        });

        viewModel.state.observe(getViewLifecycleOwner(), loginStateEnum -> {
            switch (loginStateEnum) {
                case loading:
                    LoadingUtils.showLoading(getContext());
                    break;
                case loginSuccess:
                    LoadingUtils.hideLoading();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("user", viewModel.authUser.getValue());
                    startActivity(intent);
                    break;
                case error:
                    Toast.makeText(getContext(), viewModel.message.getValue(), Toast.LENGTH_SHORT).show();
                    LoadingUtils.hideLoading();
                    break;
                default:
                    LoadingUtils.hideLoading();
                    break;
            }
        });
        LoadingUtils.showLoading(getContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}