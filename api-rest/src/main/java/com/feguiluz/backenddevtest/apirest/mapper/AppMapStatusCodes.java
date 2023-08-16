package com.feguiluz.backenddevtest.apirest.mapper;

import com.feguiluz.backenddevtest.domain.exception.status.AppResultCode;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class AppMapStatusCodes {

    public static final Map<Integer, HttpStatus> errorMappings = new HashMap<>();

    private AppMapStatusCodes() {

    }

    static {
        errorMappings.put(AppResultCode.EXTERNAL_SERVICE_ERROR.code(), HttpStatus.INTERNAL_SERVER_ERROR);
        errorMappings.put(AppResultCode.EXTERNAL_SERVICE_TIMEOUT_ERROR.code(), HttpStatus.GATEWAY_TIMEOUT);
    }
}
