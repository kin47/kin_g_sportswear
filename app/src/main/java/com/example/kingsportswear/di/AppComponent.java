package com.example.kingsportswear.di;

import com.example.kingsportswear.presentation.auth.AuthActivity;
import com.example.kingsportswear.presentation.auth.login.LoginFragment;
import com.example.kingsportswear.presentation.product_detail.ProductDetailFragment;
import com.example.kingsportswear.presentation.product_list.ProductListFragment;
import com.example.kingsportswear.presentation.profile.ProfileFragment;
import com.example.kingsportswear.presentation.auth.register.RegisterFragment;
import com.example.kingsportswear.presentation.search.SearchFragment;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {AppModule.class, DataModule.class, RepositoryModule.class})
public interface AppComponent {
    void inject(AuthActivity authActivity);
    void inject(LoginFragment profileFragment);
    void inject(RegisterFragment registerFragment);
    void inject(ProductListFragment productListFragment);
    void inject(ProductDetailFragment productDetailFragment);
    void inject(SearchFragment searchFragment);
    void inject(ProfileFragment profileFragment);
}