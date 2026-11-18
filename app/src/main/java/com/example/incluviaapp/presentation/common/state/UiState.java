package com.example.incluviaapp.presentation.common.state;

import java.util.Objects;

public sealed abstract class UiState<T>
        permits UiState.Idle, UiState.Loading, UiState.Success, UiState.Error {

    private UiState() {}

    // Kotlin `object` -> singleton
    public static final class Idle extends UiState {
        public static final Idle INSTANCE = new Idle();
        private Idle() {}

        @Override public String toString() { return "Idle"; }
    }

    public static final class Loading extends UiState {
        public static final Loading INSTANCE = new Loading();
        private Loading() {}

        @Override public String toString() { return "Loading"; }
    }

    // Kotlin `data class Success(val message: String? = null)`
    public static final class Success extends UiState {
        private final String message; // nullable

        public Success() { this(null); }               // default parameter
        public Success(String message) { this.message = message; }

        public String getMessage() { return message; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Success)) return false;
            return Objects.equals(message, ((Success) o).message);
        }
        @Override public int hashCode() { return Objects.hashCode(message); }
        @Override public String toString() { return "Success(message=" + message + ")"; }
    }

    // Kotlin `data class Error(val message: String)`
    public static final class Error extends UiState {
        private final String message;

        public Error(String message) {
            this.message = Objects.requireNonNull(message);
        }

        public String getMessage() { return message; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Error)) return false;
            return message.equals(((Error) o).message);
        }
        @Override public int hashCode() { return message.hashCode(); }
        @Override public String toString() { return "Error(message=" + message + ")"; }
    }
}
