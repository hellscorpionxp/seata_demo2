package com.tony.conf;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DBConfiguration {

  @Bean
  @Primary
  @ConfigurationProperties("spring.datasource")
  public DataSource stockDS() {
    return new DruidDataSource();
  }

  @Bean
  public JdbcTemplate stockJdbcTemplate(DataSource ds) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
    jdbcTemplate.update("delete from storage_tbl where commodity_code = 'C00321'");
    jdbcTemplate.update("insert into storage_tbl(commodity_code, count) values ('C00321', 100)");
    return jdbcTemplate;
  }

}
