package com.feguiluz.backenddevtest.infrastructure.externalservice;

import com.feguiluz.backenddevtest.client.api.DefaultApi;
import com.feguiluz.backenddevtest.client.model.ProductDetail;
import com.feguiluz.backenddevtest.domain.externalservice.ExternalServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExternalServiceClientImplTest {

    @Mock
    private DefaultApi api;

    private ExternalServiceClient externalServiceClient;

    @BeforeEach
    void setup() {
        externalServiceClient = new ExternalServiceClientImpl(api);
    }

    @Test
    @DisplayName("Given product id when get product similar ids is called then return ids set")
    void given_product_id_when_get_product_similar_ids_is_called_then_return_ids_set() {
        Set<String> ids = new HashSet<>(Arrays.asList(new String[] {"1","2","3"}));

        when(api.getProductSimilarids(anyString())).thenReturn(ids);

        final Set<String> result = externalServiceClient.getProductSimilarIds("1");

        assertThat(result, is(notNullValue()));
        assertThat(result, is(equalToObject(ids)));

        verify(api, times(1)).getProductSimilarids(anyString());
    }

    @Test
    @DisplayName("Given product id when get product detail by product id is called then return product detail")
    void given_product_id_when_get_product_detail_by_product_id_is_called_then_return_product_detail() {

        when(api.getProductProductId(anyString())).thenReturn(generateProductDetail());

        final ProductDetail result = externalServiceClient.getProductDetailByProductId("1");

        assertThat(result, is(notNullValue()));

        verify(api, times(1)).getProductProductId(anyString());
    }

    private ProductDetail generateProductDetail() {
        ProductDetail productDetail = new ProductDetail();
        return productDetail.id("1").name("product1").price(BigDecimal.TEN).availability(true);
    }


}
