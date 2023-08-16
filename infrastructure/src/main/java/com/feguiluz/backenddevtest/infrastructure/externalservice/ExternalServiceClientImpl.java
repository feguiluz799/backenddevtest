package com.feguiluz.backenddevtest.infrastructure.externalservice;

import com.feguiluz.backenddevtest.client.api.DefaultApi;
import com.feguiluz.backenddevtest.client.model.ProductDetail;
import com.feguiluz.backenddevtest.domain.exception.AppException;
import com.feguiluz.backenddevtest.domain.exception.status.AppResultCode;
import com.feguiluz.backenddevtest.domain.externalservice.ExternalServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException.GatewayTimeout;

import java.net.SocketTimeoutException;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExternalServiceClientImpl implements ExternalServiceClient {

    private final DefaultApi api;

    @Override
    public Set<String> getProductSimilarIds(String productId) throws AppException{
        log.debug("ExternalServiceClientImpl.getProductSimilarIds with productId"+productId);
        try {
            return api.getProductSimilarids(productId);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (isTimeoutException(e)) {
                throw new AppException(AppResultCode.EXTERNAL_SERVICE_TIMEOUT_ERROR);
            }
            throw new AppException(AppResultCode.EXTERNAL_SERVICE_ERROR);
        }
    }

    @Override
    public ProductDetail getProductDetailByProductId(String productId) throws AppException{
        log.debug("ExternalServiceClientImpl.getProductDetailByProductId with productId"+productId);
        try {
            return api.getProductProductId(productId);
        } catch (HttpClientErrorException e) {
            log.error(e.getMessage());
            if (isTimeoutException(e)) {
                throw new AppException(AppResultCode.EXTERNAL_SERVICE_TIMEOUT_ERROR);
            }
            throw new AppException(AppResultCode.EXTERNAL_SERVICE_ERROR);
        }
    }

    private boolean isTimeoutException(final Exception ex) {
        return ex instanceof SocketTimeoutException
                || ex instanceof GatewayTimeout
                || ex.getCause() instanceof SocketTimeoutException
                || ex.getCause() instanceof GatewayTimeout;
    }
}
