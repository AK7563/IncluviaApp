package com.example.incluviaapp.core.network;

import android.content.Context;

import com.example.incluviaapp.BuildConfig;

import kotlin.Lazy;
import kotlin.LazyKt;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    private Context appContext = null;

    public NetworkClient(Context context){
        if (appContext == null){
            synchronized (this){
                setAppContext(context.getApplicationContext());
            }
        }
    }

    public NetworkClient(){}

    public Context requireContext() {
        return appContext;
    }

    public void setAppContext(Context appContext) {
        this.appContext = appContext;
    }

    private static final HttpLoggingInterceptor httpLogger =
        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);

    private static final OkHttpClient buildOkHttpClient =
        new OkHttpClient.Builder()
        .addInterceptor(httpLogger)
        .build();


    static Lazy<Retrofit> retrofit = LazyKt.lazy(() -> new Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(buildOkHttpClient)
        .build()
    );

    public static <T> T createService(Class<T> service){
        return retrofit.getValue().create(service);
    }

}
