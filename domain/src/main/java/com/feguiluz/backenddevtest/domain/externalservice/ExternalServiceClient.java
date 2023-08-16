package com.feguiluz.backenddevtest.domain.externalservice;

import com.feguiluz.backenddevtest.client.model.ProductDetail;
import com.feguiluz.backenddevtest.domain.exception.AppException;

import java.util.Set;

public interface ExternalServiceClient {

    Set<String> getProductSimilarIds(String productId) throws AppException;

    ProductDetail getProductDetailByProductId(String productId) throws AppException;

}
