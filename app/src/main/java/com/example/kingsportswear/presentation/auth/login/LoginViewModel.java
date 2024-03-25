package com.example.kingsportswear.presentation.auth.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kingsportswear.MyApp;
import com.example.kingsportswear.R;
import com.example.kingsportswear.domain.repository.AuthRepository;
import com.example.kingsportswear.utils.ResultSetCallback;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {
    @Inject
    AuthRepository authRepository;
    private final MutableLiveData<LoginStateEnum> _state = new MutableLiveData<>();
    private final MutableLiveData<String> _message = new MutableLiveData<>();
    private final MutableLiveData<FirebaseUser> _authUser = new MutableLiveData<>();
    public LiveData<LoginStateEnum> state = _state;
    public LiveData<String> message = _message;
    public LiveData<FirebaseUser> authUser = _authUser;

    @Inject
    public LoginViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
        _state.postValue(LoginStateEnum.init);
        _message.postValue("");
        _authUser.postValue(null);
    }

    public void logIn(String email, String password) {
        _state.postValue(LoginStateEnum.loading);
        authRepository.logIn(email, password, new ResultSetCallback<>() {
            @Override
            public void onSuccess(FirebaseUser result) {
                _authUser.postValue(result);
                _state.postValue(LoginStateEnum.loginSuccess);
            }

            @Override
            public void onError(Exception e) {
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    _message.postValue(MyApp.getAppResources().getString(R.string.wrong_email_or_pass));
                } else if (e instanceof FirebaseNetworkException) {
                    _message.postValue(MyApp.getAppResources().getString(R.string.no_internet_connection));
                } else {
                    _message.postValue(e.getMessage());
                }
                _state.postValue(LoginStateEnum.error);
            }
        });
    }
}
