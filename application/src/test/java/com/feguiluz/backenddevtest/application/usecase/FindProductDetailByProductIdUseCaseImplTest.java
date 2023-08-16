package com.feguiluz.backenddevtest.application.usecase;

import com.feguiluz.backenddevtest.application.mapper.ProductDetailDTOMapper;
import com.feguiluz.backenddevtest.client.model.ProductDetail;
import com.feguiluz.backenddevtest.domain.entity.ProductDetailDTO;
import com.feguiluz.backenddevtest.domain.externalservice.ExternalServiceClient;
import com.feguiluz.backenddevtest.domain.usecase.FindProductDetailByProductIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindProductDetailByProductIdUseCaseImplTest {

    @Mock
    private ExternalServiceClient externalServiceClient;

    @Mock
    private ProductDetailDTOMapper mapper;

    private FindProductDetailByProductIdUseCase findProductDetailByProductIdUseCase;

    @BeforeEach
    void setup() {
        findProductDetailByProductIdUseCase = new FindProductDetailByProductIdUseCaseImpl(externalServiceClient, mapper);
    }

    @Test
    @DisplayName("Given a product id when execute is called then product detail dto is returned")
    void given_a_product_id_when_execute_is_called_then_product_detail_dto_is_returned() {
        when(externalServiceClient.getProductDetailByProductId(anyString())).thenReturn(generateProductDetail());
        when(mapper.asProductDetailDTO(any(ProductDetail.class))).thenReturn(generateProductDetailDTO());

        final ProductDetailDTO productDetailDTO = findProductDetailByProductIdUseCase.execute("1");

        assertThat(productDetailDTO, is(notNullValue()));

        verify(externalServiceClient, times(1)).getProductDetailByProductId(anyString());
        verify(mapper, times(1)).asProductDetailDTO(any(ProductDetail.class));
    }

    private ProductDetailDTO generateProductDetailDTO() {
        return new ProductDetailDTO("1", "product1", BigDecimal.TEN, true);
    }

    private ProductDetail generateProductDetail() {
        ProductDetail productDetail = new ProductDetail();
        return productDetail.id("1").name("product1").price(BigDecimal.TEN).availability(true);
    }
}
