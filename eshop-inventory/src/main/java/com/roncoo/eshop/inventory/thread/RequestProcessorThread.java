package com.roncoo.eshop.inventory.thread;

import com.roncoo.eshop.inventory.request.ProductInventoryCacheRefreshRequest;
import com.roncoo.eshop.inventory.request.ProductInventoryDBUpdateRequest;
import com.roncoo.eshop.inventory.request.Request;
import com.roncoo.eshop.inventory.request.RequestQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

public class RequestProcessorThread implements Callable<Boolean> {
    private static final Logger logger = LoggerFactory.getLogger(RequestProcessorThread.class);
    /**
     * 自己监控的内存队列
     */
    private ArrayBlockingQueue<Request> queue;


    public RequestProcessorThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            while (true){
                Request request = queue.take();

                logger.info("工作线程处理请求，商品id={}",request.getProductId());
                RequestQueue requestQueue = RequestQueue.getInstance();
                final Map<Integer, Boolean> flagMap = requestQueue.getFlagMap();
                if (request instanceof ProductInventoryDBUpdateRequest){
                    flagMap.put(request.getProductId(), true);
                } else if (request instanceof ProductInventoryCacheRefreshRequest){
                    final Boolean flag = flagMap.get(request.getProductId());
                    if (flag == null){
                        flagMap.put(request.getProductId(), false);
                    }
                    //如果是缓存刷新的请求
                    if (flag != null && flag){
                        flagMap.put(request.getProductId(), false);
                    }

                    if (flag != null && !flag){
                        //对于这种读请求，直接过滤掉
                        return true;
                    }
                }

                request.process();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
       return true;
    }
}
