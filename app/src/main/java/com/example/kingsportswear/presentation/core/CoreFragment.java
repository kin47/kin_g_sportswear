package com.example.kingsportswear.presentation.core;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kingsportswear.R;
import com.example.kingsportswear.databinding.FragmentCoreBinding;

public class CoreFragment extends Fragment {
    private FragmentCoreBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // disable swiping
        binding.coreFragmentViewPager.setUserInputEnabled(false);

        binding.coreFragmentViewPager.setAdapter(new CorePagerAdapter(getChildFragmentManager(), getLifecycle()));
        binding.bottomNavView.setOnItemSelectedListener(item -> {
            handleTab(item.getItemId());
            return true;
        });
    }

    void handleTab(int itemId) {
        switch (itemId) {
            case R.id.nav_home:
                binding.coreFragmentViewPager.setCurrentItem(0, false);
                break;
            case R.id.nav_search:
                binding.coreFragmentViewPager.setCurrentItem(1, false);
                break;
            case R.id.nav_cart:
                binding.coreFragmentViewPager.setCurrentItem(2, false);
                break;
            case R.id.nav_profile:
                binding.coreFragmentViewPager.setCurrentItem(3, false);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}