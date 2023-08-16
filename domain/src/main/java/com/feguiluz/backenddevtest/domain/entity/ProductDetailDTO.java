package com.feguiluz.backenddevtest.domain.entity;

import java.math.BigDecimal;

public record ProductDetailDTO(String id, String name, BigDecimal price, boolean availability) {
}
