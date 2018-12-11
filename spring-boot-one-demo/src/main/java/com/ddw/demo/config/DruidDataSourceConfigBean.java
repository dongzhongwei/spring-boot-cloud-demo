package com.ddw.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@ConditionalOnProperty(name = "demo.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
@ConfigurationProperties(DruidDataSourceConfigBean.PREFIX)
public class DruidDataSourceConfigBean {

    public static final String PREFIX = "demo.datasource";

    private final List<DruidDataSource> items = new ArrayList<>();

    public List<DruidDataSource> getItems() {
        return items;
    }


}
