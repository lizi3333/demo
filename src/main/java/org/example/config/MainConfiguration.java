package org.example.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan("org.example.service")
@MapperScan("org.example.mapper")
public class MainConfiguration {
    @Bean
    public DataSource dataSource(){
        return new PooledDataSource("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/study", "root", "123456");
    }
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource source) {
        SqlSessionFactoryBean factory=new SqlSessionFactoryBean();
        factory.setDataSource(source);
        return factory;
    }
}
