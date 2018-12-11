package com.ddw.demo.autoload.cache.condition;

import com.ddw.demo.autoload.cache.entity.UserDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Pageable;

/**
 * 查询条件
 * 
 * @author jiayu.qiu
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserCondition extends UserDO {

    private static final long serialVersionUID = -5111314038991538777L;

    private Pageable pageable;
}
