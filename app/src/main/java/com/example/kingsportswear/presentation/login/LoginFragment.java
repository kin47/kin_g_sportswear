package com.example.kingsportswear.presentation.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

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
    public void onStart() {
        super.onStart();
        viewModel.checkIfUserIsLoggedIn();
        if (viewModel.getState().getValue() == LoginStateEnum.loginSuccess) {
            NavHostFragment.findNavController(LoginFragment.this)
                    .navigate(R.id.action_LoginFragment_to_CoreFragment);
        }
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
            LoadingUtils.showLoading(getContext());
            viewModel.logIn(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    NavHostFragment.findNavController(LoginFragment.this)
                            .navigate(R.id.action_LoginFragment_to_CoreFragment);
                } else {
                    try {
                        Toast.makeText(getContext(), getContext().getString(R.string.wrong_email_or_pass), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getContext(), getContext().getString(R.string.error_system), Toast.LENGTH_SHORT).show();
                    }
                }
                LoadingUtils.hideLoading();
            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}