
package com.ddw.demo.autoload.cache.mapper.temp;

import com.jarvis.cache.annotation.Cache;

/**
 * 
 * 通用Mapper加缓存的例子
 * @author: jiayu.qiu
 */
public interface BaseMapper<T, PK> {

    @Cache(expire = 3600, expireExpression = "null == #retVal ? 600: 3600", key = "#target.getCacheName() +'-byid-' + #args[0]")
    T getById(PK id);
}
