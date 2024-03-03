package com.example.kingsportswear.presentation.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kingsportswear.MyApp;
import com.example.kingsportswear.R;
import com.example.kingsportswear.databinding.FragmentProfileBinding;

import javax.inject.Inject;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    @Inject
    ProfileViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((MyApp) getActivity().getApplication()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnLogout.setOnClickListener(view1 -> {
            viewModel.signOut();
            if (viewModel.getState().getValue() == ProfileStateEnum.logoutSuccess) {
                NavHostFragment.findNavController(ProfileFragment.this)
                        .navigate(R.id.action_CoreFragment_to_LoginFragment);
            } else {
                // show error message
                Toast.makeText(getContext(), viewModel.getMessage().getValue(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}