package com.example.incluviaapp.core.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCall {
    public static <T> void executeApiCall(
            Call<T> apiCall,
            ApiCallResponse<T> callBack
    ){
        apiCall.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                T body = response.body();
                if(body == null){
                    callBack.onEmptyBody(new IllegalStateException("Response Body Empty"));
                    return;
                }
                callBack.onSuccess(response);
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
    public static <T> void executeApiCall(
            Call<T> apiCall,
            ApiCallResponse<T> callBack,
            Context context
    ) {
        if(context == null){
            callBack.onFailure(new IllegalStateException("Context not found"));
            return;
        }
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = manager.getActiveNetwork();
        if (network == null){
            callBack.onFailure(new Exception("Not connected"));
            return;
        }
        NetworkCapabilities capabilities =
                manager.getNetworkCapabilities(network);
        if (capabilities == null) {
            callBack.onFailure(new Exception("Not connected"));
            return;
        }
        executeApiCall(apiCall, callBack);
    }
}
