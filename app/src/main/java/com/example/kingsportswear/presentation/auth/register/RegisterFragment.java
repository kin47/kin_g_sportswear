package com.example.kingsportswear.presentation.auth.register;

import android.os.Bundle;
import android.util.Log;
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
import com.example.kingsportswear.databinding.FragmentRegisterBinding;
import com.example.kingsportswear.utils.DialogUtils;
import com.example.kingsportswear.utils.LoadingUtils;

import javax.inject.Inject;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    @Inject
    RegisterViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((MyApp) getActivity().getApplication()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvSignupRedirectToLogin.setOnClickListener(view1 -> NavHostFragment.findNavController(RegisterFragment.this).navigateUp());
        binding.btnSignup.setOnClickListener(v -> {
            String email = binding.etRegisterInputEmail.getText().toString();
            String password = binding.etRegisterInputPassword.getText().toString();
            if(email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getContext(), getString(R.string.please_fill_all_field), Toast.LENGTH_SHORT).show();
            } else {
                viewModel.signUp(email, password);
            }
        });

        viewModel.state.observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case loading:
                    LoadingUtils.showLoading(getContext());
                    break;
                case registerSuccess:
                    LoadingUtils.hideLoading();
                    DialogUtils.showInformationDialog(getContext(),
                            getString(R.string.register_success),
                            getString(R.string.register_success_dialog_description),
                            () -> {
                                NavHostFragment.findNavController(RegisterFragment.this).navigateUp();
                            });
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}