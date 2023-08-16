package com.feguiluz.backenddevtest.domain.exception;

import com.feguiluz.backenddevtest.domain.exception.status.AppResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AppException extends RuntimeException {

    private final @Getter AppResult appResult;

    public AppException(final AppResult appResult, final Throwable t) {
        super(t);
        this.appResult = appResult;
    }

}
