package com.ddw.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ConfigurationProperties(DataSourceConfigBean.PREFIX)
public class DataSourceConfigBean {

    public static final String PREFIX = "demo.datasource";

    private Class<? extends DataSource> type;

    private final List<DruidDataSource> druid = new ArrayList<>();

    private final List<HikariDataSource> hikari = new ArrayList<>();

    public List<? extends DataSource> getDatasource() {
        if (this.getType() == DruidDataSource.class){
            return getDruid();
        } else {
            return getHikari();
        }
    }

}
