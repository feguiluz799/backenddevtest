package com.feguiluz.backenddevtest.domain.exception.status;

public class AppResultCode {

    public static final AppResult EXTERNAL_SERVICE_ERROR =
            new AppResult(301, "An external service error occurred", "An error has occurred during the external service program execution");

    public static final AppResult EXTERNAL_SERVICE_TIMEOUT_ERROR =
            new AppResult(302, "External service did not respond in time", "A timeout has occurred during the request to external service");

    public AppResultCode() {

    }

}
