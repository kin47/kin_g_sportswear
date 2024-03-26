package com.example.kingsportswear.presentation.core;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.kingsportswear.presentation.cart.CartFragment;
import com.example.kingsportswear.presentation.home.HomeFragment;
import com.example.kingsportswear.presentation.profile.ProfileFragment;
import com.example.kingsportswear.presentation.search.SearchFragment;

public class CorePagerAdapter extends FragmentStateAdapter {
    public CorePagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new SearchFragment();
            case 2:
                return new CartFragment();
            case 3:
                return new ProfileFragment();
            default:
                return new HomeFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 4;
    }
}
