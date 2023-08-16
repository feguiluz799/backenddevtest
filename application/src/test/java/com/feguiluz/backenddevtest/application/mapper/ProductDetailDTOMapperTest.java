package com.feguiluz.backenddevtest.application.mapper;

import com.feguiluz.backenddevtest.client.model.ProductDetail;
import com.feguiluz.backenddevtest.domain.entity.ProductDetailDTO;
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
class ProductDetailDTOMapperTest {

    private ProductDetailDTOMapper productDetailDTOMapper;

    @BeforeEach
    void setup() {
        productDetailDTOMapper = new ProductDetailDTOMapperImpl();
    }

    @Test
    @DisplayName("Given a product detail when as product detail is called then product detail dto is returned")
    void given_a_product_detail_when_as_product_detail_is_called_then_product_detail_dto_is_returned() {
        ProductDetailDTO productDetailDTO = productDetailDTOMapper.asProductDetailDTO(generateProductDetail());
        assertThat(productDetailDTO, is(notNullValue()));
    }

    private ProductDetail generateProductDetail() {
        ProductDetail productDetail = new ProductDetail();
        return productDetail.id("1").name("product1").price(BigDecimal.TEN).availability(true);
    }

}
