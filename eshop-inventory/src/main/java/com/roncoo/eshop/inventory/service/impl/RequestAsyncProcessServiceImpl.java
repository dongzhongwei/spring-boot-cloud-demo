package com.roncoo.eshop.inventory.service.impl;

import com.roncoo.eshop.inventory.request.Request;
import com.roncoo.eshop.inventory.request.RequestQueue;
import com.roncoo.eshop.inventory.service.RequestAsyncProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;

@Service
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService {
    private static final Logger logger = LoggerFactory.getLogger(RequestAsyncProcessServiceImpl.class);

    @Override
    public void process(Request request) {
        try {
            //做请求的路由 根据请求商品的Id，路由的对应的内存队列中
            ArrayBlockingQueue<Request> queue = getRoutingQueue(request.getProductId());
            queue.put(request);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private ArrayBlockingQueue<Request> getRoutingQueue(Integer productId){
        RequestQueue requestQueue = RequestQueue.getInstance();

        String key = productId.toString();

        int h;

        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);

        int index = (requestQueue.queueSize() - 1) & hash;

        logger.info("路由内存队列，商品id={},队列索引={}",productId,index);

        return requestQueue.getQueue(index);

    }
}
