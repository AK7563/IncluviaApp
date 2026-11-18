package com.example.incluviaapp.data.network;

import com.example.incluviaapp.data.response.auth.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface AuthService {
    @GET("/user")
    public Call<List<UserResponse>> getUsers(

    );
}
