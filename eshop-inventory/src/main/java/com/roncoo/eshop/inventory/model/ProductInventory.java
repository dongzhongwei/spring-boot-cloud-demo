package com.roncoo.eshop.inventory.model;

public class ProductInventory {

    private Integer productId;

    private Long inventoryCut;

    public ProductInventory() {
    }

    public ProductInventory(Integer productId, Long inventoryCut) {
        this.productId = productId;
        this.inventoryCut = inventoryCut;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer produectId) {
        this.productId = produectId;
    }

    public Long getInventoryCut() {
        return inventoryCut;
    }

    public void setInventoryCut(Long inventoryCut) {
        this.inventoryCut = inventoryCut;
    }
}
