package com.example.incluviaapp.presentation.feature.test.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.incluviaapp.core.base.BaseViewModel;
import com.example.incluviaapp.core.network.ApiCallResponse;
import com.example.incluviaapp.data.repository.TestingRepository;
import com.example.incluviaapp.data.requests.testing.TestingRequest;
import com.example.incluviaapp.data.response.testing.TestingResponse;
import com.example.incluviaapp.presentation.common.state.DataUiState;

import java.util.List;

import retrofit2.Response;

public class TestingViewModel extends BaseViewModel {
    private MutableLiveData<DataUiState<TestingResponse>> _testingModel = new MutableLiveData<>();
    public  LiveData       <DataUiState<TestingResponse>>  testingModel = _testingModel;

    private final TestingRepository repo;

    public TestingViewModel(TestingRepository repo){
        this.repo = repo;
    }
    public TestingViewModel(){
        this.repo = new TestingRepository();
    }

    public void getMetar(){
        runConcurrent(()->{
            TestingRequest request = new TestingRequest("KJFK", "json");
            ApiCallResponse<List<TestingResponse>> callback = new ApiCallResponse<>() {
                public void onSuccess(Response<List<TestingResponse>> result) {
                    assert result.body() != null;
                    _testingModel.postValue(new DataUiState.Success<>(result.body().get(0)));
                }

                public void onFailure(Throwable t) {
                    _testingModel.postValue(new DataUiState.Error<>(t.getMessage()));
                }

                public void onEmptyBody(Throwable t) {
                    onFailure(t);
                }
            };
            repo.getData(
                request,
                callback
            );
        });
    }

}
