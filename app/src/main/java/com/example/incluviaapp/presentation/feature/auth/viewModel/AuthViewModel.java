package com.example.incluviaapp.presentation.feature.auth.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.incluviaapp.core.base.BaseViewModel;
import com.example.incluviaapp.core.network.ApiCallResponse;
import com.example.incluviaapp.data.repository.AuthRepository;
import com.example.incluviaapp.data.response.auth.UserResponse;
import com.example.incluviaapp.presentation.common.state.DataUiState;

import java.util.List;

import retrofit2.Response;


public class AuthViewModel extends BaseViewModel {
    private final MutableLiveData<DataUiState<List<UserResponse>>> _testModel = new MutableLiveData<>();
    public LiveData<DataUiState<List<UserResponse>>> testModel = _testModel;
    AuthRepository repo;

    public AuthViewModel(){
        this.repo = new AuthRepository();
    }

    public AuthViewModel(AuthRepository repository){
        this.repo = repository;
    }

    public void getData(){
        runConcurrent(()->{
            repo.getUser(
                new ApiCallResponse<>() {
                    @Override
                    public void onSuccess(Response<List<UserResponse>> result) {
                        _testModel.postValue(new DataUiState.Success<>(result.body()));
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }

                    @Override
                    public void onEmptyBody(Throwable t) {

                    }
                }
            );
        });
    }
}
