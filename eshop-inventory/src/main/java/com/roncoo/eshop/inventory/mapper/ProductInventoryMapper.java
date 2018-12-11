package com.roncoo.eshop.inventory.mapper;

import com.roncoo.eshop.inventory.model.ProductInventory;
import org.apache.ibatis.annotations.Param;

public interface ProductInventoryMapper {

    void updateProductInventory(ProductInventory inventoryCut);

    ProductInventory findProductInventory(@Param("productId") Integer productId);
}
