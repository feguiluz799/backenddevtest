package com.feguiluz.backenddevtest.apirest.mapper;

import com.feguiluz.backenddevtest.domain.entity.ProductDetailDTO;
import com.feguiluz.openapi.model.ProductDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDetailMapper {

    ProductDetail asProductDetail(ProductDetailDTO productDetailDTO);

}
