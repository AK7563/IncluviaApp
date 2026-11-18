package com.example.incluviaapp.presentation.common.state;

import java.util.Objects;

public sealed abstract class DataUiState<T> permits DataUiState.Idle, DataUiState.Loading, DataUiState.Success, DataUiState.Error {

    private DataUiState() {}

    public static final class Idle<T> extends DataUiState<T> {
        private static final Idle<?> INSTANCE = new Idle<>();
        private Idle() {}

        @SuppressWarnings("unchecked")
        public static <T> Idle<T> instance() { return (Idle<T>) INSTANCE; }

        @Override public String toString() { return "Idle"; }
    }

    public static final class Loading<T> extends DataUiState<T> {
        private static final Loading<?> INSTANCE = new Loading<>();
        private Loading() {}

        @SuppressWarnings("unchecked")
        public static <T> Loading<T> instance() { return (Loading<T>) INSTANCE; }

        @Override public String toString() { return "Loading"; }
    }

    public static final class Success<T> extends DataUiState<T> {
        private final T data;
        private final String message; // nullable

        public Success(T data) { this(data, null); }   // default parameter
        public Success(T data, String message) {
            this.data = data;
            this.message = message;
        }

        public T getData() { return data; }
        public String getMessage() { return message; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Success<?> other)) return false;
            return Objects.equals(data, other.data) && Objects.equals(message, other.message);
        }
        @Override public int hashCode() { return Objects.hash(data, message); }
        @Override public String toString() {
            return "Success(data=" + data + ", message=" + message + ")";
        }
    }

    public static final class Error<T> extends DataUiState<T> {
        private final String message;

        public Error(String message) { this.message = Objects.requireNonNull(message); }

        public String getMessage() { return message; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Error<?> other)) return false;
            return message.equals(other.message);
        }
        @Override public int hashCode() { return message.hashCode(); }
        @Override public String toString() { return "Error(message=" + message + ")"; }
    }
}
