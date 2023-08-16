package com.feguiluz.backenddevtest.apirest.mapper;

import com.feguiluz.backenddevtest.domain.entity.ProductDetailDTO;
import com.feguiluz.openapi.model.ProductDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(MockitoExtension.class)
class ProductDetailMapperTest {

    private ProductDetailMapper productDetailMapper;

    @BeforeEach
    void setup() {
        productDetailMapper = new ProductDetailMapperImpl();
    }

    @Test
    @DisplayName("Given a product detail dto when as product detail is called then product detail is returned")
    void given_a_product_detail_dto_when_as_product_detail_is_called_then_product_detail_is_returned() {
        ProductDetail productDetail = productDetailMapper.asProductDetail(generateProductDetailDTO());
        assertThat(productDetail, is(notNullValue()));
    }

    private ProductDetailDTO generateProductDetailDTO() {
        return new ProductDetailDTO("1", "product1", BigDecimal.TEN, true);
    }

}
