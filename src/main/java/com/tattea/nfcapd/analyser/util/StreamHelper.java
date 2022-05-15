package com.tattea.nfcapd.analyser.util;

import com.tattea.nfcapd.analyser.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.function.Function;

@Slf4j
public class StreamHelper {

    public static <T, R> Function<T, R> wrap(CheckedFunction<T, R> function) {
        return t -> {
            try {
                return function.apply(t);
            } catch (IOException e) {
                throw new ApplicationException(e.getMessage());
            }
        };
    }

    @FunctionalInterface
    public interface CheckedFunction<T, R> {
        R apply(T t) throws IOException;
    }
}