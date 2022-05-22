package com.tattea.nfcapd.analyser.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }
}
