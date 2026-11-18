package com.example.incluviaapp.data.network;

import com.example.incluviaapp.data.response.testing.TestingResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TestingService {

    @GET("/api/data/metar")
    public Call<List<TestingResponse>> getWeather(
        @Query("ids")    String icao,
        @Query("format") String format
    );
}
