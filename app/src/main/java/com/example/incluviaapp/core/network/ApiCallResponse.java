package com.example.incluviaapp.core.network;

import retrofit2.Response;

public interface ApiCallResponse<T>{
    void onSuccess(Response<T> result);
    void onFailure(Throwable t);
    void onEmptyBody(Throwable t);
}