package com.ddw.demo.config;

import io.shardingsphere.api.config.MasterSlaveRuleConfiguration;
import io.shardingsphere.api.config.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

@Configuration
public class DataSourceConfig {

    private final DataSourceConfigBean dataSourceConfigBean;

    public DataSourceConfig(DataSourceConfigBean dataSourceConfigBean) {
        this.dataSourceConfigBean = dataSourceConfigBean;
    }

//    @Bean
    public DataSource dataSource() throws SQLException {
        List<? extends DataSource> datasource = dataSourceConfigBean.getDatasource();
        int datasourceSize = datasource.size();
        if (datasourceSize == 1){
            return datasource.get(0);
        } else if (datasourceSize > 1){
            DataSource master = datasource.get(0);
            String ruleConfigurationName = "ds_master_slave";
            String masterDataSourceName = "ds_master";
            String slaveDataSourceNamePrefix = "ds_slave";
            String slaveDataSourceName;
            List<String> slaveDataSourceNames = new ArrayList<>();
            Map<String, DataSource> dataSourceMap = new HashMap<>();
            dataSourceMap.put(masterDataSourceName,master);
            for (int i = 1; i < datasource.size(); i++) {
                slaveDataSourceName = slaveDataSourceNamePrefix+(i-1);
                slaveDataSourceNames.add(slaveDataSourceName);
                dataSourceMap.put(slaveDataSourceName, datasource.get(i));
            }
            // 配置读写分离规则
            MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration(ruleConfigurationName, masterDataSourceName, slaveDataSourceNames);
            // 获取数据源对象
            DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, masterSlaveRuleConfig, new HashMap<>(), new Properties());
            // 配置Order表规则
//            TableRuleConfiguration userTableRuleConfiguration = new TableRuleConfiguration();
//            userTableRuleConfiguration.setLogicTable("user");
//            userTableRuleConfiguration.setActualDataNodes();
            // 配置分库 + 分表策略
//            userTableRuleConfiguration.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
//            userTableRuleConfiguration.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "user${id % 2}"));
            return dataSource;
        } else {
            return null;
        }
    }

}