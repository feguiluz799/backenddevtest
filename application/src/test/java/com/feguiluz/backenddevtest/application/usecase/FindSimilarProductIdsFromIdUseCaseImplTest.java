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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindSimilarProductIdsFromIdUseCaseImplTest {

    @Mock
    private ExternalServiceClient externalServiceClient;

    @Mock
    private FindProductDetailByProductIdUseCase findProductDetailByProductIdUseCase;

    private FindSimilarProductIdsFromIdUseCaseImpl findSimilarProductIdsFromIdUseCase;

    @BeforeEach
    void setup() {
        findSimilarProductIdsFromIdUseCase = new FindSimilarProductIdsFromIdUseCaseImpl(externalServiceClient, findProductDetailByProductIdUseCase);
    }

    @Test
    @DisplayName("Given a product id when execute is called then product detail dto list is returned")
    void given_a_product_id_when_execute_is_called_then_product_detail_dto_list_is_returned() {
        Set<String> ids = new HashSet<>(Arrays.asList(new String[] {"1","2","3"}));

        when(externalServiceClient.getProductSimilarIds(anyString())).thenReturn(ids);
        when(findProductDetailByProductIdUseCase.execute(anyString())).thenReturn(generateProductDetailDTO());

        final List<ProductDetailDTO> productDetailDTOList = findSimilarProductIdsFromIdUseCase.execute("1");

        assertThat(productDetailDTOList, is(notNullValue()));
        assertThat(productDetailDTOList, is(not(empty())));

        verify(externalServiceClient, times(1)).getProductSimilarIds(anyString());
        verify(findProductDetailByProductIdUseCase, times(3)).execute(anyString());
    }

    private ProductDetailDTO generateProductDetailDTO() {
        return new ProductDetailDTO("1", "product1", BigDecimal.TEN, true);
    }



}
