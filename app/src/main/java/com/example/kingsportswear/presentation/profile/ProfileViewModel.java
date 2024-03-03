package com.example.kingsportswear.presentation.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.fragment.NavHostFragment;

import com.example.kingsportswear.domain.repository.AuthRepository;
import com.example.kingsportswear.utils.models.CustomResult;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    @Inject
    AuthRepository authRepository;
    private MutableLiveData<ProfileStateEnum> state = new MutableLiveData<>();
    private MutableLiveData<String> message = new MutableLiveData<>();

    @Inject
    public ProfileViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
        this.state.setValue(ProfileStateEnum.init);
    }

    public LiveData<ProfileStateEnum> getState() {
        return state;
    }

    public LiveData<String> getMessage() {
        return message;
    }

    public void signOut() {
        state.setValue(ProfileStateEnum.loading);
        try {
            CustomResult<Boolean> result = authRepository.signOut();
            if (result.isSuccess()) {
                state.setValue(ProfileStateEnum.logoutSuccess);
            } else {
                state.setValue(ProfileStateEnum.error);
                message.setValue(result.getException().getMessage());
            }
        } catch (Exception e) {
            state.setValue(ProfileStateEnum.error);
            message.setValue(e.getMessage());
        }
    }
}
