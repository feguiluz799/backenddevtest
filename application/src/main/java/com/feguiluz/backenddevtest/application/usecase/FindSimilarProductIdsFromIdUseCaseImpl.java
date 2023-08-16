package com.feguiluz.backenddevtest.application.usecase;

import com.feguiluz.backenddevtest.domain.entity.ProductDetailDTO;
import com.feguiluz.backenddevtest.domain.exception.AppException;
import com.feguiluz.backenddevtest.domain.externalservice.ExternalServiceClient;
import com.feguiluz.backenddevtest.domain.usecase.FindProductDetailByProductIdUseCase;
import com.feguiluz.backenddevtest.domain.usecase.FindSimilarProductIdsFromIdUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindSimilarProductIdsFromIdUseCaseImpl implements FindSimilarProductIdsFromIdUseCase {

    private final ExternalServiceClient externalServiceClient;

    private final FindProductDetailByProductIdUseCase findProductDetailByProductIdUseCase;

    @Override
    public List<ProductDetailDTO> execute(String productId) throws AppException {
        log.debug("FindSimilarProductIdsFromIdUseCaseImpl.execute with productId "+productId);
        final Set<String> similarIds = externalServiceClient.getProductSimilarIds(productId);
        final List<ProductDetailDTO> productDetailDTOList = new ArrayList<>();

        for (String id:similarIds){
            productDetailDTOList.add(findProductDetailByProductIdUseCase.execute(id));
        }
        return productDetailDTOList;
    }
}
