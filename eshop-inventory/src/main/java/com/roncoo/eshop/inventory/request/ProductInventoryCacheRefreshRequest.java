package com.roncoo.eshop.inventory.request;

import com.roncoo.eshop.inventory.model.ProductInventory;
import com.roncoo.eshop.inventory.service.ProductInventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductInventoryCacheRefreshRequest implements Request {
    private static final Logger logger = LoggerFactory.getLogger(ProductInventoryCacheRefreshRequest.class);

    private Integer productId;

    private ProductInventoryService productInventoryService;

    public ProductInventoryCacheRefreshRequest(Integer productId, ProductInventoryService productInventoryService) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        //从数据库中查询最新的库存数量
        final ProductInventory productInventory = productInventoryService.findProductInventory(productId);
        logger.info("已查询到最新的商品库存数量，商品id={},商品库存数量={}",productId,productInventory.getInventoryCut());
        //库存中最新数量，刷新到redis中
        productInventoryService.setProductInventoryCache(productInventory);

    }

    @Override
    public Integer getProductId() {
        return productId;
    }
}
