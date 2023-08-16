package com.feguiluz.backenddevtest.domain.usecase.common;

public interface CommonUseCase<T,R> {

    R execute(T parameter);

}
