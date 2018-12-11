package com.ddw.demo;

import com.ddw.demo.config.DataSourceConfigBean;
import com.ddw.demo.custom.repository.MyRepositoryImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableConfigurationProperties({DataSourceConfigBean.class})
@MapperScan("com.ddw.demo.autoload.cache.mapper")
@EnableJpaRepositories(value = {"com.ddw.demo.shardingsphere","com.ddw.demo.jpa"},repositoryBaseClass = MyRepositoryImpl.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}
}
