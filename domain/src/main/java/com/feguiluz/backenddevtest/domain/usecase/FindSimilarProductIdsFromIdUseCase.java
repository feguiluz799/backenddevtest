package com.feguiluz.backenddevtest.domain.usecase;

import com.feguiluz.backenddevtest.domain.entity.ProductDetailDTO;
import com.feguiluz.backenddevtest.domain.exception.AppException;
import com.feguiluz.backenddevtest.domain.usecase.common.CommonUseCase;

import java.util.List;

public interface FindSimilarProductIdsFromIdUseCase extends CommonUseCase<String, List<ProductDetailDTO>> {

    @Override
    List<ProductDetailDTO> execute(String productId) throws AppException;
}
