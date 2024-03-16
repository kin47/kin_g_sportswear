package com.example.kingsportswear.presentation.register;

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
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvSignupRedirectToLogin.setOnClickListener(view1 -> NavHostFragment.findNavController(RegisterFragment.this)
                .navigate(R.id.action_RegisterFragment_to_LoginFragment));
        binding.btnSignup.setOnClickListener(v -> {
            String email = binding.etRegisterInputEmail.getText().toString();
            String password = binding.etRegisterInputPassword.getText().toString();
            LoadingUtils.showLoading(getContext());
            viewModel.signUp(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DialogUtils.showInformationDialog(getContext(),
                            getContext().getString(R.string.register_success),
                            getContext().getString(R.string.register_success_dialog_description),
                            () -> {
                                NavHostFragment.findNavController(RegisterFragment.this)
                                        .navigate(R.id.action_RegisterFragment_to_LoginFragment);
                            });
                } else {
                    Log.w("RegisterFragment", "signUp:failure", task.getException());
                    Toast.makeText(getContext(), getContext().getString(R.string.email_already_exists),
                            Toast.LENGTH_SHORT).show();
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