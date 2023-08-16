package com.feguiluz.backenddevtest.domain.usecase;

import com.feguiluz.backenddevtest.domain.entity.ProductDetailDTO;
import com.feguiluz.backenddevtest.domain.exception.AppException;
import com.feguiluz.backenddevtest.domain.usecase.common.CommonUseCase;

public interface FindProductDetailByProductIdUseCase extends CommonUseCase<String, ProductDetailDTO> {

    @Override
    ProductDetailDTO execute(String productId) throws AppException;
}
