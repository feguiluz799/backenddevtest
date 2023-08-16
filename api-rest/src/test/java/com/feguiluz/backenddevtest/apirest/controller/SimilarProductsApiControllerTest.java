package com.feguiluz.backenddevtest.apirest.controller;

import com.feguiluz.backenddevtest.apirest.mapper.ProductDetailMapper;
import com.feguiluz.backenddevtest.domain.entity.ProductDetailDTO;
import com.feguiluz.backenddevtest.domain.exception.AppException;
import com.feguiluz.backenddevtest.domain.exception.status.AppResultCode;
import com.feguiluz.backenddevtest.domain.usecase.FindSimilarProductIdsFromIdUseCase;
import com.feguiluz.openapi.model.ProductDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimilarProductsApiControllerTest {

    @Mock
    private FindSimilarProductIdsFromIdUseCase useCase;

    @Mock
    private ProductDetailMapper mapper;

    private SimilarProductsApiController similarProductsApiController;

    private List<ProductDetailDTO> productDetailDTOList;

    private AtomicLong atomicLong;

    @BeforeEach
    void setup() {
        similarProductsApiController = new SimilarProductsApiController(useCase, mapper);
        productDetailDTOList = getProductDetailDTOList();
        atomicLong = new AtomicLong(1);
    }

    @Test
    @DisplayName("Given product id when get product similar is called then return ok response entity")
    void given_product_id_when_get_product_similar_is_called_then_return_ok_response_entity() {
        when(useCase.execute(anyString())).thenReturn(productDetailDTOList);
        when(mapper.asProductDetail(any(ProductDetailDTO.class))).thenReturn(generateProductDetail());

        final ResponseEntity<Set<ProductDetail>> responseEntity = similarProductsApiController.getProductSimilar("1");

        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getStatusCode().is2xxSuccessful(), is(true));

        verify(useCase, times(1)).execute(anyString());
        verify(mapper, times(3)).asProductDetail(any(ProductDetailDTO.class));
    }

    @Test
    @DisplayName("Given product id when get product similar is called and use case caught exception then return 500 status code response entity")
    void given_product_id_when_get_product_similar_is_called_and_use_case_then_return_500_status_code_response_entity() {
        ResponseEntity<Set<ProductDetail>> responseEntity;
        when(useCase.execute(anyString())).thenThrow(new AppException(AppResultCode.EXTERNAL_SERVICE_ERROR));

        responseEntity = similarProductsApiController.getProductSimilar("1");

        assertThat(responseEntity, is(notNullValue()));
        assertThat(responseEntity.getStatusCode().is5xxServerError(), is(true));

        verify(useCase, times(1)).execute(anyString());
    }

    private List<ProductDetailDTO> getProductDetailDTOList() {
        final List<ProductDetailDTO> productDetailDTOList = new ArrayList<>();
        productDetailDTOList.add(new ProductDetailDTO("1", "product1", BigDecimal.TEN, true));
        productDetailDTOList.add(new ProductDetailDTO("2", "product2", BigDecimal.TEN, true));
        productDetailDTOList.add(new ProductDetailDTO("3", "product3", BigDecimal.TEN, true));
        return productDetailDTOList;
    }

    private ProductDetail generateProductDetail() {
        final ProductDetail productDetail = new ProductDetail();
        productDetail.setId(atomicLong.toString());
        productDetail.setName("product"+atomicLong);
        productDetail.setPrice(BigDecimal.TEN);
        productDetail.setAvailability(true);
        atomicLong.incrementAndGet();
        return productDetail;
    }

}
