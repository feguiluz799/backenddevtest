package com.feguiluz.backenddevtest.application.mapper;

import com.feguiluz.backenddevtest.client.model.ProductDetail;
import com.feguiluz.backenddevtest.domain.entity.ProductDetailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDetailDTOMapper {

    ProductDetailDTO asProductDetailDTO(ProductDetail productDetail);

}
