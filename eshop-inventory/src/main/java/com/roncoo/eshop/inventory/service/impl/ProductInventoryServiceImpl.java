package com.roncoo.eshop.inventory.service.impl;

import com.roncoo.eshop.inventory.mapper.ProductInventoryMapper;
import com.roncoo.eshop.inventory.model.ProductInventory;
import com.roncoo.eshop.inventory.service.ProductInventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {
    private static final Logger logger = LoggerFactory.getLogger(ProductInventoryServiceImpl.class);

    private final ProductInventoryMapper productInventoryMapper;

    private final StringRedisTemplate stringRedisTemplate;

    public ProductInventoryServiceImpl(ProductInventoryMapper productInventoryMapper, StringRedisTemplate stringRedisTemplate) {
        this.productInventoryMapper = productInventoryMapper;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 修改DB中的商品库存
     * @param productInventory
     */
    @Override
    public void updateProductInventory(ProductInventory productInventory) {
        productInventoryMapper.updateProductInventory(productInventory);
        logger.info("已修改数据库中库存：商品id={},商品库存数量={}",productInventory.getProductId(),productInventory.getInventoryCut());
    }

    /**
     * 删除商品库存的缓存
     * @param productInventory
     */
    @Override
    public void removeProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        logger.info("已删除redis缓存，key={}",key);
        stringRedisTemplate.delete(key);
    }

    @Override
    public ProductInventory findProductInventory(Integer productId) {
        return productInventoryMapper.findProductInventory(productId);
    }

    @Override
    public void setProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        stringRedisTemplate.opsForValue().set(key,productInventory.getInventoryCut().toString());
        logger.info("已更新商品库存的缓存，商品id={},商品库存数量={},key={}",productInventory.getProductId(),productInventory.getInventoryCut(),key);
    }

    @Override
    public ProductInventory getProductInventoryCache(Integer productId) {
        Long inventoryCnt = 0L;
        String key = "product:inventory:" + productId;
        final String result = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.hasText(result)){
            inventoryCnt = Long.parseLong(result);
            return new ProductInventory(productId,inventoryCnt);
        }
        return null;
    }
}
