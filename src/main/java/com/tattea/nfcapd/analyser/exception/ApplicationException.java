package com.tattea.nfcapd.analyser.exception;

public class ApplicationException extends RuntimeException {

    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }
}
