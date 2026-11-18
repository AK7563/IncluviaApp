package com.example.incluviaapp.data.repository;

import com.example.incluviaapp.core.network.ApiCall;
import com.example.incluviaapp.core.network.ApiCallResponse;
import com.example.incluviaapp.core.network.NetworkClient;
import com.example.incluviaapp.data.network.TestingService;
import com.example.incluviaapp.data.requests.testing.TestingRequest;
import com.example.incluviaapp.data.response.testing.TestingResponse;

import java.util.List;

public class TestingRepository {

    private final TestingService service;

    public TestingRepository() {
        this.service = NetworkClient.createService(TestingService.class);
    }
    public TestingRepository(TestingService service) {
        this.service = service;
    }

    public void getData(
        TestingRequest request,
        ApiCallResponse<List<TestingResponse>> callback
    ){
        ApiCall.executeApiCall(
            service.getWeather(
                request.icao(),
                request.format()
            ),
            callback
        );
    }
}
