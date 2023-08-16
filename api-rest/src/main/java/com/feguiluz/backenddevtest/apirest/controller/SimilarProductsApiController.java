package com.feguiluz.backenddevtest.apirest.controller;

import com.feguiluz.backenddevtest.apirest.mapper.ProductDetailMapper;
import com.feguiluz.backenddevtest.domain.entity.ProductDetailDTO;
import com.feguiluz.backenddevtest.domain.exception.AppException;
import com.feguiluz.backenddevtest.domain.usecase.FindSimilarProductIdsFromIdUseCase;
import com.feguiluz.openapi.api.ProductApi;
import com.feguiluz.openapi.model.ProductDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.feguiluz.backenddevtest.apirest.mapper.AppMapStatusCodes.errorMappings;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SimilarProductsApiController implements ProductApi {

    private final FindSimilarProductIdsFromIdUseCase useCase;

    private final ProductDetailMapper mapper;

    @Override
    public ResponseEntity<Set<ProductDetail>> getProductSimilar(String productId) {
        log.debug("SimilarProductsApiController.getProductSimilar with productId "+productId);
        final Set<ProductDetail> productDetailSet = new HashSet<>();
        List<ProductDetailDTO> productDetailDTOList = null;

        try {
            productDetailDTOList = useCase.execute(productId);
        } catch (AppException e) {
            log.error("SimilarProductsApiController: Error calling external service with productId "+productId);
            return new ResponseEntity<>(errorMappings.get(e.getAppResult().code()));
        }

        for (ProductDetailDTO productDetailDTO:productDetailDTOList) {
            productDetailSet.add(mapper.asProductDetail(productDetailDTO));
        }
        return ResponseEntity.ok(productDetailSet);
    }
}
