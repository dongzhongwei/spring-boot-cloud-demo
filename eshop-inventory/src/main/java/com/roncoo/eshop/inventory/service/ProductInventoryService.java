package com.roncoo.eshop.inventory.service;

import com.roncoo.eshop.inventory.model.ProductInventory;

public interface ProductInventoryService {

    /**
     * 修改DB中的商品库存
     * @param productInventory
     */
    void updateProductInventory(ProductInventory productInventory);

    /**
     * 删除商品库存的缓存
     * @param productInventory
     */
    void removeProductInventoryCache(ProductInventory productInventory);

    ProductInventory findProductInventory(Integer productId);

    void setProductInventoryCache(ProductInventory productInventory);


    ProductInventory getProductInventoryCache(Integer productId);

}
