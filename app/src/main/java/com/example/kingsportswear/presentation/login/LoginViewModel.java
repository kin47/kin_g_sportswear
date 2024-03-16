package com.example.kingsportswear.presentation.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.kingsportswear.domain.repository.AuthRepository;
import com.example.kingsportswear.utils.models.CustomResult;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {
    @Inject
    AuthRepository authRepository;

    private final MutableLiveData<LoginStateEnum> state = new MutableLiveData<>();
    private final MutableLiveData<String> message = new MutableLiveData<>();

    @Inject
    public LoginViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
        this.state.setValue(LoginStateEnum.init);
        this.message.setValue("");
    }

    public LiveData<LoginStateEnum> getState() {
        return state;
    }

    public LiveData<String> getMessage() {
        return message;
    }

    public void checkIfUserIsLoggedIn() {
        try {
            CustomResult<Boolean> result = authRepository.checkIfUserIsLoggedIn();
            if (result.isSuccess()) {
                if (result.getData()) {
                    state.setValue(LoginStateEnum.loginSuccess);
                } else {
                    state.setValue(LoginStateEnum.idle);
                }
            } else {
                state.setValue(LoginStateEnum.error);
                message.setValue(result.getException().getMessage());
            }
        } catch (Exception e) {
            state.setValue(LoginStateEnum.error);
            message.setValue(e.getMessage());
        }
    }

    public Task<AuthResult> logIn(String email, String password) {
        return authRepository.logIn(email, password);
    }
}
