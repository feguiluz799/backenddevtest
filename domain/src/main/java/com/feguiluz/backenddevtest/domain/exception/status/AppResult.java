package com.feguiluz.backenddevtest.domain.exception.status;

import java.io.Serializable;

public record AppResult(Integer code, String title, String message) implements Serializable {

    private static final long serialVersionUID = 1L;

}
