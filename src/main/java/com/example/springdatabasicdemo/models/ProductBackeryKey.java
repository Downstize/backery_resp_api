package com.example.springdatabasicdemo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductBackeryKey implements Serializable {
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "backery_id")
    private Long backeryId;

    // Constructors

    public ProductBackeryKey() {
    }

    public ProductBackeryKey(Long productId, Long backeryId) {
        this.productId = productId;
        this.backeryId = backeryId;
    }

    // Getters and Setters

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getBackeryId() {
        return backeryId;
    }

    public void setBackeryId(Long backeryId) {
        this.backeryId = backeryId;
    }

    // Equals and Hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductBackeryKey)) return false;
        ProductBackeryKey that = (ProductBackeryKey) o;
        return Objects.equals(getProductId(), that.getProductId()) &&
                Objects.equals(getBackeryId(), that.getBackeryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getBackeryId());
    }
}
