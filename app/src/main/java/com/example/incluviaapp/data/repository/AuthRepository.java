package com.example.incluviaapp.data.repository;

import android.content.Context;

import com.example.incluviaapp.core.network.ApiCall;
import com.example.incluviaapp.core.network.ApiCallResponse;
import com.example.incluviaapp.core.network.NetworkClient;
import com.example.incluviaapp.data.network.AuthService;
import com.example.incluviaapp.data.response.auth.UserResponse;

import java.util.List;

import retrofit2.Response;

public class AuthRepository {

    private final AuthService service;
    public AuthRepository(AuthService service){
        this.service = service;
    }

    public AuthRepository(){
        this.service = NetworkClient.createService(AuthService.class);
    }

    public void getUser(
        ApiCallResponse<List<UserResponse>> callback
    ){
        ApiCall.executeApiCall(
            service.getUsers(),
            callback
        );
    }

}

