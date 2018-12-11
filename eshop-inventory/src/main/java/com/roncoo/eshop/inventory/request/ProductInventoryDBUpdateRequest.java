package com.roncoo.eshop.inventory.request;

import com.roncoo.eshop.inventory.model.ProductInventory;
import com.roncoo.eshop.inventory.service.ProductInventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductInventoryDBUpdateRequest implements Request {
    private static final Logger logger = LoggerFactory.getLogger(ProductInventoryDBUpdateRequest.class);

    private ProductInventory productInventory;

    private ProductInventoryService productInventoryService;

    public ProductInventoryDBUpdateRequest(ProductInventory productInventory, ProductInventoryService productInventoryService) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        logger.info("数据库更新请求开始执行，商品id={}",productInventory.getProductId());
        //先删除缓存
        productInventoryService.removeProductInventoryCache(productInventory);
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //修改数据库中库存
        productInventoryService.updateProductInventory(productInventory);

    }

    @Override
    public Integer getProductId() {
        return productInventory.getProductId();
    }
}
