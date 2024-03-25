package com.example.kingsportswear.presentation.auth.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kingsportswear.MyApp;
import com.example.kingsportswear.R;
import com.example.kingsportswear.domain.repository.AuthRepository;
import com.example.kingsportswear.presentation.auth.login.LoginStateEnum;
import com.example.kingsportswear.utils.ResultSetCallback;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {
    @Inject
    AuthRepository authRepository;

    private final MutableLiveData<RegisterStateEnum> _state = new MutableLiveData<>();
    private final MutableLiveData<String> _message = new MutableLiveData<>();
    public LiveData<RegisterStateEnum> state = _state;
    public LiveData<String> message = _message;

    @Inject
    public RegisterViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void signUp(String email, String password) {
        _state.postValue(RegisterStateEnum.loading);
        authRepository.signUp(email, password, new ResultSetCallback<>() {
            @Override
            public void onSuccess(AuthResult result) {
                _state.postValue(RegisterStateEnum.registerSuccess);
            }

            @Override
            public void onError(Exception e) {
                if (e instanceof FirebaseAuthUserCollisionException) {
                    _message.postValue(MyApp.getAppResources().getString(R.string.email_already_exists));
                } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    if (e.getMessage().contains("The email address is badly formatted.")) {
                        _message.postValue(MyApp.getAppResources().getString(R.string.invalid_email));
                    } else {
                        _message.postValue(MyApp.getAppResources().getString(R.string.weak_password));
                    }
                } else if (e instanceof FirebaseNetworkException) {
                    _message.postValue(MyApp.getAppResources().getString(R.string.no_internet_connection));
                } else {
                    _message.postValue(e.getMessage());
                }
                _state.postValue(RegisterStateEnum.error);
            }
        });
    }
}
