package com.example.incluviaapp.core.base;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BaseViewModel extends ViewModel {

    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final MutableLiveData<Throwable> error = new MutableLiveData<>();

    @FunctionalInterface
    protected interface Task {
        void run() throws Exception;
    }

    protected void runConcurrent(boolean showLoading, Executor dispatcher, Task task) {
        executor.submit(() -> dispatcher.execute(() -> {
            if (showLoading) setLoading(true);
            try {
                task.run();
            } catch (Throwable t) {
                emitError(t);
            } finally {
                if (showLoading) setLoading(false);
            }
        }));
    }

    protected void runConcurrent(Task block) {
        runConcurrent(true, executor, block);
    }

    protected void runConcurrent(boolean showLoading, Task block) {
        runConcurrent(showLoading, executor, block);
    }

    protected void setLoading(boolean value) { loading.postValue(value); }
    protected void emitError(Throwable t)    { error.postValue(t); }

    public LiveData<Boolean> getLoading() { return loading; }
    public LiveData<Throwable> getError() { return error; }

    @Override
    protected void onCleared() {
        executor.shutdownNow(); // cancels running tasks, like viewModelScope does
    }
}
