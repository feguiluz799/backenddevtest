package com.feguiluz.backenddevtest.application.usecase;

import com.feguiluz.backenddevtest.application.mapper.ProductDetailDTOMapper;
import com.feguiluz.backenddevtest.client.model.ProductDetail;
import com.feguiluz.backenddevtest.domain.entity.ProductDetailDTO;
import com.feguiluz.backenddevtest.domain.exception.AppException;
import com.feguiluz.backenddevtest.domain.externalservice.ExternalServiceClient;
import com.feguiluz.backenddevtest.domain.usecase.FindProductDetailByProductIdUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindProductDetailByProductIdUseCaseImpl implements FindProductDetailByProductIdUseCase {

    private final ExternalServiceClient externalServiceClient;

    private final ProductDetailDTOMapper mapper;

    @Override
    public ProductDetailDTO execute(String productId) throws AppException {
        log.debug("FindProductDetailByProductIdUseCaseImpl.execute with productId "+productId);

        final ProductDetail productDetail = externalServiceClient.getProductDetailByProductId(productId);
        return mapper.asProductDetailDTO(productDetail);
    }
}
